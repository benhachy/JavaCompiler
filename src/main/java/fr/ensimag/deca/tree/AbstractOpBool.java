package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
            System.out.println("::AbstractOpCm.java:: verifyExpr");
            AbstractExpr rOp = getRightOperand();
            AbstractExpr lOp = getLeftOperand();
            Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
            Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
            if(!(type1.isBoolean() && type2.isBoolean()))
            {
                throw new ContextualError("Les opérations booleennes ne supportent que des booleens", getLocation());
            }
            return new BooleanType(SymbolTable.creerSymbol("OpBool"));
    }
    @Override
    public void codeGenCode(DecacCompiler compiler,DVal C, boolean b,Label E) {
        // AbstractExpr rOp = getRightOperand();
        // AbstractExpr lOp = getLeftOperand();
        // lOp.codeGenCode(compiler, C, b, E);
        // compiler.addInstruction(new LOAD(C, Register.getR(0)));
        // rOp.codeGenCode(compiler, C, b, E);
        // this.codeGenOpBool(compiler,b, 0);
        // compiler.addInstruction(new LOAD(C, Register.getR(0)));
        // compiler.addInstruction(new CMP(new ImmediateInteger(0), Register.getR(0)));
        // if(b)
        // {
        //     compiler.addInstruction(new BNE(E));
        // }
        // else
        // {
        //     compiler.addInstruction(new BEQ(E));
        // } 
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();

    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        
        codeGenExpr(compiler, 2);
    }

}
