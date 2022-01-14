package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.instructions.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.deca.tree.IntLiteral;
import fr.ensimag.ima.*;
import fr.ensimag.ima.pseudocode.DVal;


/**
 * @author gl03
 * @date 01/01/2022
 */
public class Plus extends AbstractOpArith {
    public Plus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenOp(DecacCompiler compiler, GPRegister leftOperand, GPRegister rightOperand,int n) {
        compiler.addInstruction(new ADD(rightOperand,leftOperand));
    }

  
    @Override
    protected String getOperatorName() {
        return "+";
    }

}