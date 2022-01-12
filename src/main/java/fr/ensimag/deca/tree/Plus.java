package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.instructions.*;
import fr.ensimag.deca.DecacCompiler;
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
    protected void codeGenPrint(DecacCompiler compiler){
        System.out.println(":: Plus.java :: codeGenPrint");
        
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        compiler.addInstruction(new ADD(Register.getR(1),Register.getR(0)));
        rOp.codeGenPrint(compiler);//r1 contint 2 
        //compiler.addInstruction(new ADD(Register.getR(1),Register.getR(0)));
        //r1 contient 1
        if(lOp instanceof IntLiteral)
        {
            compiler.addInstruction(new ADD(Register.getR(1),Register.getR(0)));
            lOp.codeGenPrint(compiler);
            compiler.addInstruction(new ADD(Register.getR(0),Register.getR(1)));
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
    @Override
    protected String getOperatorName() {
        return "+";
    }
}
