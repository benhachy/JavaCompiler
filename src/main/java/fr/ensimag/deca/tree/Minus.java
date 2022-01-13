package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;

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
    
}
