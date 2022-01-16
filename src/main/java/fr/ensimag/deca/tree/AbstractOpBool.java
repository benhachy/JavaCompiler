package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }
    public static int numInst =0;

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
            System.out.println("::AbstractOpCm.java:: verifyExpr");
            AbstractExpr rOp = getRightOperand();
            AbstractExpr lOp = getLeftOperand();
            Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
            Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
            if(!(type1.isBoolean() && type2.isBoolean()))
            {
                throw new ContextualError("Les opérations booleennes ne supportent que des booleens", getLocation());
            }
            setType(new BooleanType(SymbolTable.creerSymbol(getOperatorName())));
            return getType();
    }
    @Override
    public void codeGenExpr(DecacCompiler compiler,int n){
        System.out.println("::AbstractOpBool.java:: codeGenExpr");
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        System.out.println("::AbstractOpBool.java:: codeGenInst");
        Label trueVar = new Label("trueVar"+numInst);
        Label falseVar = new Label("falseVar"+numInst);
        Label nextVar = new Label("nextVar"+numInst);
        numInst++;
        codeGenOpBool(compiler,null,null,true,trueVar,falseVar,1);
        compiler.addInstruction(new BRA(falseVar));
        compiler.addLabel(trueVar);
        compiler.addInstruction(new LOAD(new ImmediateInteger(1),Register.getR(2)));
        compiler.addInstruction(new BRA(nextVar));
        compiler.addLabel(falseVar);
        compiler.addInstruction(new LOAD(new ImmediateInteger(0),Register.getR(2)));
        compiler.addLabel(nextVar);
    }

}
