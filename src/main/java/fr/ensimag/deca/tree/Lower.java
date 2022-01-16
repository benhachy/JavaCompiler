package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BLT;
import fr.ensimag.ima.pseudocode.instructions.BGT;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Lower extends AbstractOpIneq {

    private static int cmpEtiquetes=0; 

    public Lower(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "<";
    }

    @Override
    public void  codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        System.out.println("::Greater.java:: codeGenOpBool");
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        rOp.codeGenExpr(compiler, 2);
        lOp.codeGenExpr(compiler, 3);
        if(rOp.getType().isFloat() && lOp.getType().isInt())
        {
            compiler.addInstruction(new FLOAT(Register.getR(3), Register.getR(3)));
        }
        else if(rOp.getType().isInt() && lOp.getType().isFloat())
        {
            compiler.addInstruction(new FLOAT(Register.getR(2), Register.getR(2)));
        }
        compiler.addInstruction(new CMP(Register.getR(2),Register.getR(3)));
        if (b){
            compiler.addInstruction(new BLT(E));
        }
        else{
            compiler.addInstruction(new BGT(E));
        }
    }


    @Override
    protected void codeGenInst(DecacCompiler compiler){
        getRightOperand().codeGenExpr(compiler, 2);
        getLeftOperand().codeGenExpr(compiler, 3);
        compiler.addInstruction(new CMP(Register.getR(3),Register.getR(2)));
        Label loadTrue = new Label("loadTrueLT."+cmpEtiquetes);
        Label finCmp = new Label("finComparationLT."+cmpEtiquetes);
        compiler.addInstruction(new BLT(loadTrue));
        new IntLiteral(0).codeGenExpr(compiler,2);
        compiler.addInstruction(new BRA(finCmp));
        compiler.addLabel(loadTrue);
        new IntLiteral(1).codeGenExpr(compiler,2);
        compiler.addLabel(finCmp);
        cmpEtiquetes++;
    }

}
