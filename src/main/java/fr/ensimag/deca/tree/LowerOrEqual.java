package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BLE;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class LowerOrEqual extends AbstractOpIneq {

    private static int cmpEtiquetes=0; 

    public LowerOrEqual(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "<=";
    }

    public void  codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        System.out.println("::Greater.java:: codeGenOpBool");
        getLeftOperand().codeGenExpr(compiler, 3);
        getRightOperand().codeGenExpr(compiler, 2);
        compiler.addInstruction(new CMP(Register.getR(2),Register.getR(3)));
        if (b){
            compiler.addInstruction(new BLE(E));
        }
    }
    @Override
    protected void codeGenInst(DecacCompiler compiler){
        getRightOperand().codeGenExpr(compiler, 2);
        getLeftOperand().codeGenExpr(compiler, 3);
        compiler.addInstruction(new CMP(Register.getR(3),Register.getR(2)));
        Label loadTrue = new Label("loadTrueLE."+cmpEtiquetes);
        Label finCmp = new Label("finComparationLE."+cmpEtiquetes);
        compiler.addInstruction(new BLE(loadTrue));
        new IntLiteral(0).codeGenExpr(compiler,1);
        compiler.addInstruction(new BRA(finCmp));
        compiler.addLabel(loadTrue);
        new IntLiteral(1).codeGenExpr(compiler,1);
        compiler.addLabel(finCmp);
        cmpEtiquetes++;
    }

}
