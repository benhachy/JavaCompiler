package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.BSR;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.NEW;
import fr.ensimag.ima.pseudocode.instructions.POP;

import java.io.PrintStream;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.LEA;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.BooleanType;



/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class New extends AbstractExpr {
    public AbstractIdentifier type;
    public New(AbstractIdentifier type) {
        this.type = type;
    }
    public  Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError{
        if(compiler.get(type.getName()) == null){
            throw new ContextualError("Classe  non définie ", type.getLocation());
        }
        Type classe = compiler.get(type.getName()).getType();
        if(!classe.isClass() || classe == null){
            throw new ContextualError("Il faut un constructeur pour initialiser ", getLocation());
        }
        type.setDefinition(compiler.getClass(type.getName()));
        setType(classe);

        return (ClassType)classe;
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
    }
    @Override
    public void decompile(IndentPrintStream s) {
        
    }
    @Override
    protected void codeGenInst(DecacCompiler compiler){
        //comment obtenir le nb des atributs de l'objet???
        //on allue une structure de la taille de l'objet dans la tas
        compiler.addInstruction(new NEW(type.getClassDefinition().getNumberOfFields()+1,Register.getR(2)));
        //on verifie si la tas est plein
        compiler.addInstruction(new BOV(new Label("pile_pleine")));
        //on charge l'address de la class dans la table des methodes sur R0
        compiler.addInstruction(new LEA(Identifier.getVariableAddress(type.getName()), Register.getR(0)));
        //on store l'address de la class sur la premiere posicion de l'address reserve pour l'objet dans la tas
        compiler.addInstruction(new STORE(Register.getR(0),new RegisterOffset(0,Register.getR(2))));
        //on fait push de R2 pour appelle le segment init
        compiler.addInstruction(new PUSH(Register.getR(2)));
        //on appel le segment pour l'initialization du program
        compiler.addInstruction(new BSR(new Label("init."+type.getName().getName())));
        //apres l'initialization on faire pop pour restorer le registre non scratch
        compiler.addInstruction(new POP(Register.getR(2)));
    }

    
}