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
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

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
    public Selection(AbstractIdentifier type,AbstractExpr expr){
        this.type = type;
        this.expr=expr;
    }
    @Override
    public  void codeGenAssign(DecacCompiler compiler,int n){
        if(expr.getType().isClass()){
            expr.codeGenExpr(compiler, 3);
            type.codeGenAssign(compiler,n);
            compiler.addInstruction(new STORE( Register.getR(2),new RegisterOffset(type.getFieldDefinition().getIndex()+1, Register.getR(3))));
        }else{
            type.codeGenAssign(compiler,n);
        }      
    }
    @Override
    public  void codeGenExpr(DecacCompiler compiler,int n){

        if(expr.getType().isClass()){
            expr.codeGenExpr(compiler, 3);
            compiler.addInstruction(new LOAD( new RegisterOffset(type.getFieldDefinition().getIndex()+1, Register.getR(3)),Register.getR(n)));
        }else{
        }      
    }
    @Override 
    protected void codeGenInst(DecacCompiler compiler) {
        this.codeGenExpr(compiler, 2);
    }
    @Override
    public void codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n){
        this.codeGenExpr(compiler, 0);
        if(b){
            compiler.addInstruction(new CMP(new ImmediateInteger(1),Register.getR(0)));
            compiler.addInstruction(new BEQ(E));
        }
        else{
            compiler.addInstruction(new CMP(new ImmediateInteger(0),Register.getR(0)));
            compiler.addInstruction(new BEQ(E));
        }
        
    }
    
    @Override
    protected void codeGenPrint(DecacCompiler compiler){
        this.codeGenExpr(compiler, 1);
    }

    public  Type verifyExpr(DecacCompiler compiler,
    EnvironmentExp localEnv, ClassDefinition currentClass)
    throws ContextualError{
        expr.verifyExpr(compiler, localEnv, currentClass);
        if(expr.getType().isNull()){
            throw new ContextualError("l'Objet est null ", expr.getLocation());
        }
        type.setDefinition(localEnv.get(type.getName()));
        Type expression = type.verifyAttribut(compiler,expr.getType().getName(),currentClass);
        setType(expression);
        return getType();
    }
    @Override
    public void decompile(IndentPrintStream s) {
        expr.decompile(s);
        s.print(".");
        type.decompile(s);
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