package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.SUB;

/**
 * @author gl03
 * @date 01/01/2022
 */
public class Minus extends AbstractOpArith {
    public Minus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    protected int codeGenPrint2(DecacCompiler compiler, AbstractExpr rOp, AbstractExpr lOp) {
        return 1;
    }
    @Override
    protected String getOperatorName() {
        return "-";
    }
    
    @Override
    public void codeGenOp(DecacCompiler compiler, GPRegister leftOperand, GPRegister rightOperand,int n) {
        compiler.addInstruction(new SUB(rightOperand,leftOperand));
        //compiler.addInstruction(new LOAD(leftOperand,Register.getR(n)));
    }
}
