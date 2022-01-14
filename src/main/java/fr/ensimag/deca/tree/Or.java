package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Or extends AbstractOpBool {

    public Or(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "||";
    }
    @Override
    public void  codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        System.out.println("::Or.java:: codeGenOpBool");
        AbstractExpr lOp = getLeftOperand();
        AbstractExpr rOp = getRightOperand();
        AbstractExpr OrOperand =new Not(new And( new Not(lOp), new Not(rOp)));
        OrOperand.codeGenOpBool(compiler, null, null, b, E,EFin, n);
    }
}
