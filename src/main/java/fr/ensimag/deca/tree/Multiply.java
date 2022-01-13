package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.MUL;

/**
 * @author gl03
 * @date 01/01/2022
 */
public class Multiply extends AbstractOpArith {
    public Multiply(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }
    @Override
    protected void codeGenPrint(DecacCompiler compiler){
        System.out.println(":: Plus.java :: codeGenPrint");
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        compiler.addInstruction(new MUL(Register.getR(1),Register.getR(2)));
        rOp.codeGenPrint(compiler);//r1 contint 2 
        //compiler.addInstruction(new ADD(Register.getR(1),Register.getR(0)));
        //r1 contient 1
        if(lOp instanceof IntLiteral)
        {
            compiler.addInstruction(new MUL(Register.getR(1),Register.getR(2)));
            lOp.codeGenPrint(compiler);
            compiler.addInstruction(new MUL(Register.getR(2),Register.getR(1)));
        }
        else{
            lOp.codeGenPrint(compiler);
        }
        //compiler.addInstruction(new ADD(Register.getR(0),Register.getR(1)));
        //r0 contient la valeur final
        //rOp.codeGenPrint(compiler);
        //r1 contient 5
        // 
        //compiler.addInstruction(new ADD(Register.getR(0),Register.getR(1) ));
        //r1 contient 6
        
        //affiche 6
    }

    protected int codeGenPrint2(DecacCompiler compiler, AbstractExpr rOp, AbstractExpr lOp) {
        return 1;
    }
    @Override
    protected String getOperatorName() {
        return "*";
    }

}
