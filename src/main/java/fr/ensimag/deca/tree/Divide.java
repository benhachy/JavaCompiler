package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateFloat;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.DIV;
import fr.ensimag.ima.pseudocode.instructions.QUO;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Divide extends AbstractOpArith {
    public Divide(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }
    protected int codeGenPrint2(DecacCompiler compiler, AbstractExpr rOp, AbstractExpr lOp) {
        return 1;
    }

    @Override
    protected String getOperatorName() {
        return "/";
    }
    @Override
    public void codeGenOp(DecacCompiler compiler, GPRegister leftOperand, GPRegister rightOperand,int n) {
        if(getLeftOperand().getType().isFloat()||getRightOperand().getType().isFloat()){
            compiler.addInstruction(new DIV(rightOperand,leftOperand));
            compiler.addInstruction(new CMP(new ImmediateFloat(0),rightOperand));
        }else{
            compiler.addInstruction(new QUO(rightOperand,leftOperand));
            compiler.addInstruction(new CMP(new ImmediateInteger(0),rightOperand));
        }
        
        compiler.addInstruction(new BEQ(new Label("division_zero")));
    }
}
