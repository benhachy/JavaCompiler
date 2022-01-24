package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.RTS;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.instructions.TSTO;
import fr.ensimag.ima.pseudocode.LabelOperand;

import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclMethod extends AbstractDeclMethod {
    AbstractIdentifier type;
    AbstractIdentifier name;
    ListDeclParam paramDecl;
    AbstractMethodBody methodBody;

    public DeclMethod(AbstractIdentifier type, AbstractIdentifier name, ListDeclParam paramDecl,
            AbstractMethodBody methodBody) {
        this.type = type;
        this.name = name;
        this.paramDecl = paramDecl;
        this.methodBody = methodBody;
    }
    public AbstractIdentifier getName(){
        return name;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        name.decompile(s);
        s.print("(");
        paramDecl.decompile(s);
        s.print(")");
        s.println("{");
        methodBody.decompile(s);
        s.println("}");

    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        name.prettyPrint(s, prefix, false);
        paramDecl.prettyPrint(s, prefix, false);
        methodBody.prettyPrint(s, prefix, false);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        name.iter(f);
        paramDecl.iter(f);
        methodBody.iter(f);
    }

    public void creerEtStockerLabel(DecacCompiler compiler, DeclClass declClass) {
        // creer l'etiquete du methode
        Label label = new Label("code." + declClass.getIdentifier().getName().getName() + "." + name.getName());
        // insertion des etiquetes des methodes sur la table des methodes
        compiler.addInstruction(new LOAD(new LabelOperand(label), Register.getR(0)));
        compiler.addInstruction(new STORE(Register.getR(0), new RegisterOffset(Register.getPosGB(), Register.GB)));
        Register.updatePosGB();
    }

    // generer le code ass pour le methode
    public void genCodeMethode(DecacCompiler compiler, DeclClass declClass) {

        // creer l'etiquete du methode
        compiler.addLabel(
                new Label("code." + declClass.getIdentifier().getName().getName() + "." + name.getName().getName()));
        // test de debordement de la pile
        compiler.addInstruction(new TSTO(2), "test de debordement de la pile");
        compiler.addInstruction(new BOV(new Label("pile_pleine")));
        compiler.addInstruction(new PUSH(Register.getR(2)));
        compiler.addInstruction(new PUSH(Register.getR(3)));
        Label returnLabel = new Label(
                "fin." + declClass.getIdentifier().getName().getName() + "." + name.getName().getName());
        compiler.setReturnLabel(returnLabel);
        paramDecl.codeListDeclParam(compiler);
        // appel de la gen code pour le method body
        methodBody.codeGenMethodBody(compiler);
        // etiquete du fin de methode
        compiler.addLabel(returnLabel);
        compiler.addComment("Restauration des registres");
        compiler.addInstruction(new POP(Register.getR(3)));
        compiler.addInstruction(new POP(Register.getR(2)));
        compiler.addInstruction(new RTS());
    }

    @Override
    public int verifyMethod(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass, int indice)
            throws ContextualError {
        type.setDefinition(compiler.getDefinition(type.getName()));
        Type expectedReturn = compiler.get(type.getName()).getType();
        type.setType(expectedReturn);
        Signature signature = new Signature();
        paramDecl.verifyListParam(compiler, signature);
        MethodDefinition method = new MethodDefinition(expectedReturn, getLocation(), signature, indice);
        ClassDefinition superClass = currentClass.getSuperClass();

        int isNew = 0;
        name.setDefinition(method);
        name.setType(method.getType());
        int index = localEnv.contains(name.getName());
        if (index >= 1) {
            System.out.println(currentClass.getType().getName() + "." + name.getName() + "..." + index);
            method.setIndex(index);
            name.setDefinition(method);
            name.setType(method.getType());
            isNew = 0;
        } else {
            System.out.println(currentClass.getType().getName() + "." + name.getName() + "..." + indice);
            isNew = 1;
        }

        try {
            localEnv.declare(name.getName(), method);
        } catch (EnvironmentExp.DoubleDefException e) {

            throw new ContextualError("la méthode " + name.getName() + " est déjà définie", getLocation());
        }
        return isNew;
    }

    @Override
    public void verifyBody(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        type.setDefinition(compiler.getDefinition(type.getName()));
        Type expectedReturn = compiler.get(type.getName()).getType();
        Signature signature = new Signature();
        EnvironmentExp paramsEnv = paramDecl.verifyListParam(compiler, signature);
        methodBody.verifyMethodBody(compiler, localEnv, paramsEnv, currentClass, expectedReturn);
    }
}
