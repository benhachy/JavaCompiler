package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import java.time.format.SignStyle;

import org.apache.commons.lang.Validate;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.NullOperand;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.ADDSP;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.BSR;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.instructions.SUBSP;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.BooleanType;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class MethodCall extends AbstractExpr {
    AbstractExpr name;
    AbstractIdentifier methodName;
    ListExpr listExpression;

    public MethodCall(AbstractExpr name,
            AbstractIdentifier methodName,
            ListExpr listExpression) {
        this.name = name;
        this.methodName = methodName;
        this.listExpression = listExpression;
    }

    public MethodCall(AbstractIdentifier methodName,
            ListExpr listExpression) {
        this.name = new This();
        this.name.setLocation(methodName.getLocation());
        this.methodName = methodName;
        this.listExpression = listExpression;
    }

    public Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        // name : l'expression à laquelle on applique la méthode MethodName et qui prend
        // en argument lisExpression
        name.verifyExpr(compiler, localEnv, currentClass);
        if (!name.getType().isClassOrNull()) {
            throw new ContextualError(methodName.getName() + " n'est applicable que sur des objets",
                    methodName.getLocation());
        }

        ClassType classe = (ClassType) name.getType();
        // EnvironmentExp envClass = compiler.getEnv(classe.getName());
        MethodDefinition defMethod = methodName.verifyExistence(compiler, classe);
        methodName.setDefinition(defMethod);
        methodName.setType(defMethod.getType());
        Signature sig = defMethod.getSignature();
        if (listExpression.size() != sig.size()) {
            throw new ContextualError("manque de parametres dans " + methodName.getName(), methodName.getLocation());
        }
        listExpression.verifySignature(compiler, localEnv, currentClass, sig);
        setType(defMethod.getType());
        return getType();
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        name.iter(f);
        methodName.iter(f);
        listExpression.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        name.prettyPrint(s, prefix, false);
        methodName.prettyPrint(s, prefix, false);
        listExpression.prettyPrint(s, prefix, false);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        name.decompile(s);
        s.print(".");
        methodName.decompile(s);
        s.print("(");
        listExpression.decompile(s);
        s.print(")");
    }

    public void codeGenInst(DecacCompiler compiler) {
        // je pense faire push pop pour le r3
        compiler.addComment("appel à la méthode " + methodName.getName());
        compiler.addInstruction(new ADDSP(1 + listExpression.size()));
        name.codeGenExpr(compiler, 3);
        compiler.addInstruction(new STORE(Register.getR(3), new RegisterOffset(0, Register.SP)));
        int j = 0;
        for (AbstractExpr exp : listExpression.getList()) {
            exp.codeGenExpr(compiler, 3);
            compiler.addInstruction(new STORE(Register.getR(3), new RegisterOffset(-j - 1, Register.SP)));
            j++;
        }
        compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.SP), Register.getR(3)));
        compiler.addInstruction(new CMP(new NullOperand(), Register.getR(3)));
        compiler.addInstruction(new BEQ(new Label("deferencement.null")));
        compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.getR(3)), Register.getR(3)));
        // a revoir
        //System.out.println("==========" + methodName.getName() + "." + methodName.getMethodDefinition().getIndex());
        compiler.addInstruction(
                new BSR(new RegisterOffset(methodName.getMethodDefinition().getIndex(), Register.getR(3))));
        // Register.getR(3))));
        // chercher si la methode c'est deja fait sur la superclass
        /*
         * if(methodName.getMethodDefinition().getIndex()){
         * //il y a de override cherche dans la superclass
         * 
         * }else{
         * //si no
         * compiler.addInstruction(new BSR(new
         * Label("code."+name.getType().getName().getName()+"."+methodName.getName().
         * getName())));
         * }
         */
        // compiler.addInstruction(new BSR(
        // new Label("code." + name.getType().getName().getName() + "." +
        // methodName.getName().getName())));
        compiler.addInstruction(new SUBSP(1 + listExpression.size()));
    }

    @Override
    public void codeGenOpBool(DecacCompiler compiler, GPRegister leftOperand, GPRegister rightOperand, boolean b,
            Label E, Label EFin, int n) {
        // je pense faire push pop pour le r3
        compiler.addComment("appel à la méthode " + methodName.getName());
        compiler.addInstruction(new ADDSP(1 + listExpression.size()));
        name.codeGenExpr(compiler, 3);
        compiler.addInstruction(new STORE(Register.getR(3), new RegisterOffset(0, Register.SP)));
        int j = 0;
        for (AbstractExpr exp : listExpression.getList()) {
            exp.codeGenExpr(compiler, 3);
            compiler.addInstruction(new STORE(Register.getR(3), new RegisterOffset(-j - 1, Register.SP)));
            j++;
        }
        compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.SP), Register.getR(3)));
        compiler.addInstruction(new CMP(new NullOperand(), Register.getR(3)));
        compiler.addInstruction(new BEQ(new Label("deferencement.null")));
        compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.getR(3)), Register.getR(3)));
        // a revoir
        // compiler.addInstruction(new BSR(new
        // RegisterOffset(methodName.getMethodDefinition().getIndex(),Register.getR(3) )
        // ));
        // chercher si la methode c'est deja fait sur la superclass
        /*
         * if(methodName.getMethodDefinition().getIndex()){
         * //il y a de override cherche dans la superclass
         * 
         * }else{
         * //si no
         * compiler.addInstruction(new BSR(new
         * Label("code."+name.getType().getName().getName()+"."+methodName.getName().
         * getName())));
         * }
         */
        compiler.addInstruction(new BSR(
                new Label("code." + name.getType().getName().getName() + "." + methodName.getName().getName())));
        compiler.addInstruction(new SUBSP(1 + listExpression.size()));
        compiler.addInstruction(new CMP(new ImmediateInteger(1), Register.getR(0)));
        compiler.addInstruction(new BEQ(E));
    }

    public void codeGenExpr(DecacCompiler compiler, int n) {
        // je pense faire push pop pour le r3
        compiler.addComment("appel à la méthode " + methodName.getName());
        compiler.addInstruction(new ADDSP(1 + listExpression.size()));
        name.codeGenExpr(compiler, n);
        compiler.addInstruction(new STORE(Register.getR(n), new RegisterOffset(0, Register.SP)));
        int j = 0;
        for (AbstractExpr exp : listExpression.getList()) {
            exp.codeGenExpr(compiler, n);
            compiler.addInstruction(new STORE(Register.getR(n), new RegisterOffset(-j - 1, Register.SP)));
            j++;
        }
        compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.SP), Register.getR(n)));
        compiler.addInstruction(new CMP(new NullOperand(), Register.getR(n)));
        compiler.addInstruction(new BEQ(new Label("deferencement.null")));
        compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.getR(n)), Register.getR(n)));
        // a revoir
        // compiler.addInstruction(new BSR(new RegisterOffset(
        // methodName.getMethodDefinition().getIndex() ,Register.getR(3) ) ));
        compiler.addInstruction(new BSR(
                new Label("code." + name.getType().getName().getName() + "." + methodName.getName().getName())));
        compiler.addInstruction(new SUBSP(1 + listExpression.size()));
    }

    @Override
    protected void codeGenPrint(DecacCompiler compiler) {
        compiler.addComment("codeGenPrint in MethodCall");
        this.codeGenInst(compiler);
        compiler.addInstruction(new LOAD(Register.getR(0), Register.getR(1)));
    }
}
