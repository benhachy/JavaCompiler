package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.instructions.*;
import fr.ensimag.ima.pseudocode.*;


/**
 * @author gl03
 * @date 01/01/2022
 */
public class Plus extends AbstractOpArith {
    public Plus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }
 
    @Override
    protected void codeGenAdd(DecacCompiler compiler){
        compiler.addInstruction(new ADD(Register.getR(1),Register.getR(2)));
    }
    @Override
    protected String getOperatorName() {
        return "+";
    }
}
