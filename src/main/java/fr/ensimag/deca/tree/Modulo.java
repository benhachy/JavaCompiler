package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.MUL;
import fr.ensimag.ima.pseudocode.instructions.QUO;
import fr.ensimag.ima.pseudocode.instructions.SUB;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Modulo extends AbstractOpArith {

    public Modulo(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
        Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
        if(!type1.isInt() || !type2.isInt())
        {
            throw new ContextualError("Modulo ne supporte que des entiers", getLocation());
        }
        setType(type1);
        return getType();
    }


    @Override
    public void codeGenOp(DecacCompiler compiler,GPRegister leftOperand,GPRegister rightOperand,int n){
        compiler.addInstruction(new LOAD(leftOperand,Register.getR(3)));
        compiler.addInstruction(new QUO(rightOperand,leftOperand));
        compiler.addInstruction(new CMP(new ImmediateInteger(0),rightOperand));
        compiler.addInstruction(new BEQ(new Label("division_zero")));
        compiler.addInstruction(new MUL(rightOperand,leftOperand));
        compiler.addInstruction(new SUB(leftOperand,Register.getR(3)));
        compiler.addInstruction(new LOAD(Register.getR(3),leftOperand));
        
    }


    @Override
    protected String getOperatorName() {
        return "%";
    }

}
