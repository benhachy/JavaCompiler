package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class NotEquals extends AbstractOpExactCmp {

    private static int cmpEtiquetes=0; 

    public NotEquals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "!=";
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler){
        getRightOperand().codeGenExpr(compiler, 2);
        getLeftOperand().codeGenExpr(compiler, 3);
        compiler.addInstruction(new CMP(Register.getR(3),Register.getR(2)));
        Label loadFalse = new Label("loadFalseNE."+cmpEtiquetes);
        Label finCmp = new Label("finComparationNE."+cmpEtiquetes);
        compiler.addInstruction(new BEQ(loadFalse));
        new IntLiteral(1).codeGenExpr(compiler,1);
        compiler.addInstruction(new BRA(finCmp));
        compiler.addLabel(loadFalse);
        new IntLiteral(0).codeGenExpr(compiler,1);
        compiler.addLabel(finCmp);
        cmpEtiquetes++;
    }

}
