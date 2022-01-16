package fr.ensimag.deca.tree;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class And extends AbstractOpBool {

    public And(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }
    public static int numFin =0;

    @Override
    protected String getOperatorName() {
        return "&&";
    }

    @Override
    public void  codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        System.out.println("::And.java:: codeGenOpBool");
        AbstractExpr lOp = getLeftOperand();
        AbstractExpr rOp = getRightOperand();

        if (b){
            Label finAnd = new Label("finAnd"+numFin);
            ++numFin; 
            lOp.codeGenOpBool(compiler, null, null, false, finAnd,E, n);
            rOp.codeGenOpBool(compiler, null, null, true, E,EFin, n);
            compiler.addLabel(finAnd);
        }
        else{
            lOp.codeGenOpBool(compiler, null, null, false, E,EFin, n);
            rOp.codeGenOpBool(compiler, null, null, false, E,EFin, n);

        }

    }
}
