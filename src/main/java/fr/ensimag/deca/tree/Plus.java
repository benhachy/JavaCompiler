package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.instructions.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.deca.tree.IntLiteral;


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

     //protected int codeGenPrint2(DecacCompiler compiler ,AbstractExpr roperand ,AbstractExpr loperand, int debut ){
    //     //2
    //     if (debut != 0){
    //         IntLiteral rOp2= ( IntLiteral ) roperand;
    //         System.out.println(rOp2.getValue());
    //         Plus lOp4 = (Plus ) getLeftOperand();//2  
           
    //     }
    //     else{
    //         AbstractExpr rOp = getRightOperand();//3
    //         AbstractExpr lOp = getLeftOperand();//2  
    //         IntLiteral rOp2= ( IntLiteral ) rOp;
    //         System.out.println(rOp2.getValue());
    //         Plus lOp4 = (Plus ) getLeftOperand();//2  
            
    //     }
       
    //     System.out.println("::PLUS.java:: codeGenPrint2");
    //     if (loperand instanceof IntLiteral){
    //         loperand.codeGenPrint(compiler);
    //         //IntLiteral rOp2= ( IntLiteral ) rOp;
    //         //compiler.addInstruction(new LOAD(new ImmediateInteger(getValue()),Register.getR(3) ));//R3 contient 2
    //         return 0;
    //     }
    //     else{
    //     codeGenPrint2(compiler,rOp,lOp4.getRightOperand());
        
    //     compiler.addInstruction(new ADD(new ImmediateInteger(rOp2.getValue()),Register.getR(3)));//R3 contient 9

    //         //compiler.addInstruction(new ADD(rOp,Register.getR(3)));//R3 contient 9

    //         //compiler.addInstruction(new LOAD(new ImmediateInteger(getValue()),Register.getR(3) ));//R3 contient 2
    //     return 1;
    //     }
    //     return 1;
    // }
    //
    @Override
    protected String getOperatorName() {
        return "+";
    }

    @Override
    protected int codeGenPrint2(DecacCompiler compiler, AbstractExpr rOp, AbstractExpr lOp) {
        // TODO Auto-generated method stub
        return 0;
    }
}