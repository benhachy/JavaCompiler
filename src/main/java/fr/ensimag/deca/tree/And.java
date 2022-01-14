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
        // Label Operand = new Label(labelStart+numFin);
        // Label endOperand = new Label(labelEnd+numFin);
        // ++numFin;
        compiler.addLabel(E);
        // lOp.codeGenOpBool(compiler,Register.getR(0), Register.getR(0),b,E, n);
        // //lOp.codeGenExpr(compiler,n);//true
        // compiler.addInstruction(new PUSH(Register.getR(n)));
        // //compiler.addLabel(endOperand);
        // rOp.codeGenOpBool(compiler,Register.getR(0), Register.getR(0),b,E, n);//false
        // compiler.addInstruction(new LOAD(Register.getR(n) ,Register.getR(0)));
        // compiler.addInstruction(new POP(Register.getR(n)));
        // this.codeGenOpBool(compiler,Register.getR(n) ,Register.getR(0), true, endOperand, n);
        // compiler.addLabel(endOperand);
        if (b){
            lOp.codeGenOpBool(compiler, null, null, false, EFin,EFin.addFin(numFin), n);
            //compiler.addInstruction(new PUSH(Register.getR(n)));
            rOp.codeGenOpBool(compiler, null, null, true, E,EFin, n);
            // compiler.addInstruction(new LOAD(Register.getR(n) ,Register.getR(0)));
            // compiler.addInstruction(new POP(Register.getR(n)));
            compiler.addLabel(EFin);
            ++numFin;
        }
        else{
            lOp.codeGenOpBool(compiler, null, null, false, E,EFin, n);
            //compiler.addInstruction(new PUSH(Register.getR(n)));
            rOp.codeGenOpBool(compiler, null, null, false, E,EFin, n);
            // compiler.addInstruction(new LOAD(Register.getR(n) ,Register.getR(0)));
            // compiler.addInstruction(new POP(Register.getR(n)));
        }
        numFin++;
    }
}
