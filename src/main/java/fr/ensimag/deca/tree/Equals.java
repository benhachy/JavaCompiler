package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Equals extends AbstractOpExactCmp {

    private static int cmpEtiquetes=0; 

    public Equals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    public void  codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        // System.out.println("::Equals.java:: codeGenOpBool");
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
            compiler.addInstruction(new BEQ(E));
        }
        else{
            compiler.addInstruction(new BNE(E));
        }
    }
    @Override
    protected void codeGenInst(DecacCompiler compiler){
        compiler.addComment("equals");
        getRightOperand().codeGenExpr(compiler, 2);
        getLeftOperand().codeGenExpr(compiler, 3);
        compiler.addInstruction(new CMP(Register.getR(3),Register.getR(2)));
        Label loadTrue = new Label("loadTrueEQ."+cmpEtiquetes);
        Label finCmp = new Label("finComparationEQ."+cmpEtiquetes);
        compiler.addInstruction(new BEQ(loadTrue));
        new IntLiteral(0).codeGenExpr(compiler,2);
        compiler.addInstruction(new BRA(finCmp));
        compiler.addLabel(loadTrue);
        new IntLiteral(1).codeGenExpr(compiler,2);
        compiler.addLabel(finCmp);
        cmpEtiquetes++;
    }
    
    @Override
    protected String getOperatorName() {
        return "==";
    }    
    
}
