package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
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
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import java.io.PrintStream;
import fr.ensimag.deca.context.BooleanType;




/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Selection extends AbstractLValue {
    public AbstractIdentifier type;
    public AbstractExpr expr;
    public Selection(AbstractIdentifier type,AbstractExpr expr) {
        this.type = type;
        this.expr=expr;
    }
    public  void codeGenAssign(DecacCompiler compiler){
        compiler.addComment("Affectation");
        //obtenir l'address de le objet en relation a LB
        compiler.addInstruction(new LOAD(new RegisterOffset(1,Register.LB),Register.getR(2)));
        
    }
    public  Type verifyExpr(DecacCompiler compiler,
    EnvironmentExp localEnv, ClassDefinition currentClass)
    throws ContextualError{
         //à effacer je l'ai ajouter pour ne pas avoir un pb lors de la compilation delete it and do whatever u wanna do 
        expr.verifyExpr(compiler, localEnv, currentClass);
        type.setDefinition(localEnv.get(type.getName()));
        Type expression = type.verifyAttribut(compiler,expr.getType().getName());
        setType(expression);
        return getType();
    }
    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(".");
        expr.decompile(s);
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        expr.iter(f);
        type.iter(f);
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    // leaf node => nothing to do
    expr.prettyPrint(s, prefix, false);
    type.prettyPrint(s, prefix, false);
    }
}