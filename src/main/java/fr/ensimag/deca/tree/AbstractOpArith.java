package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.TSTO;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Arithmetic binary operations (+, -, /, ...)
 * 
 * @author gl03
 * @date 01/01/2022
 */
public abstract class AbstractOpArith extends AbstractBinaryExpr {

    public AbstractOpArith(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        //System.out.println("::AbstractOpArith.java:: verifyExpr");
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
        Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
        if(!(type1.isInt() && type2.isInt()) && !(type1.isFloat() && type2.isFloat())
        && !(type1.isFloat() && type2.isInt()) && !(type1.isInt() && type2.isFloat()))
        {
            throw new ContextualError("Les deux types "+type1.getName()+" et "+type2.getName()+
            " ne sont pas compatibles pour une opération arithmetique", getLocation());
        }
        if(type1.isFloat())
        {
            setType(type1);
            return type1;
        }
        setType(type2);
        return type2;
    }

    @Override
    public void codeGenExpr(DecacCompiler compiler,int n)
    {
        AbstractExpr leftOperand = getLeftOperand();
        AbstractExpr rightOperand = getRightOperand();
        leftOperand.codeGenExpr(compiler,n);
        compiler.addInstruction(new TSTO(2));
        compiler.addInstruction(new BOV(new Label("pile_pleine")));
        compiler.addInstruction(new PUSH(Register.getR(n)));
        rightOperand.codeGenExpr(compiler,n);
        compiler.addInstruction(new LOAD(Register.getR(n) ,Register.getR(0)));
        compiler.addInstruction(new POP(Register.getR(n)));
        if(rightOperand.getType().isFloat() && leftOperand.getType().isInt())
        {
            compiler.addInstruction(new FLOAT(Register.getR(n),Register.getR(n)));
        }
        else if(rightOperand.getType().isInt() && leftOperand.getType().isFloat())
        {
            compiler.addInstruction(new FLOAT(Register.getR(0),Register.getR(0)));
        }
        this.codeGenOp(compiler, Register.getR(n), Register.getR(0), n);    
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        codeGenExpr(compiler, 2);
    }

    @Override
    protected void codeGenPrint(DecacCompiler compiler) {
        codeGenInst(compiler);
        compiler.addInstruction(new LOAD(Register.getR(2) ,Register.getR(1)));
    }
    
    
}
