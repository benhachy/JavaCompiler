package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.MUL;

/**
 * @author gl03
 * @date 01/01/2022
 */

public class Multiply extends AbstractOpArith {
    public Multiply(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenOp(DecacCompiler compiler,GPRegister leftOperand,GPRegister rightOperand,int n){
        compiler.addInstruction(new MUL(rightOperand,leftOperand));
        compiler.addInstruction(new BOV(new Label("Overflow_error")));
        //compiler.addInstruction(new LOAD(rightOperand,Register.getR(n)));
    }
    @Override
    protected String getOperatorName() {
        return "*";
    }

}
