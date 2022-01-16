package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.context.BooleanType;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Not extends AbstractUnaryExpr {

    public Not(AbstractExpr operand) {
        super(operand);
    }
    public static int numNot=0;

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        System.out.println("::NOT.java:: verifyExpr");
        AbstractExpr expr = getOperand();
        Type exprType = expr.verifyExpr(compiler, localEnv, currentClass);
        if(!exprType.isBoolean())
        {
            throw new ContextualError("l'opérateur Not ne supporte que des booleens", getLocation());
        }
        setType(new BooleanType(SymbolTable.creerSymbol(getOperatorName())));
        return getType();
    }


    @Override
    protected String getOperatorName() {
        return "!";
    }
    @Override
    public void  codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        System.out.println("::Not.java:: codeGenOpBool");
        AbstractExpr operand = getOperand();
        operand.codeGenOpBool(compiler, null, null, !b, E,EFin, n);
        
    }
    @Override
    protected void codeGenInst(DecacCompiler compiler)
    {
        // System.out.println("::Not.java:: codeGenInst");
        // Label beginNot = new Label("beginNot"+numNot);
        // Label endNot = new Label("endNot"+numNot);
        // compiler.addLabel(beginNot);
        // codeGenOpBool(compiler, null, null,false , beginNot, endNot, 2);
        // compiler.addInstruction(new BRA(endNot));
        // compiler.addLabel(endNot);
        System.out.println("::AbstractOpBool.java:: codeGenInst");
        Label trueVar = new Label("trueVar"+AbstractOpBool.numInst);
        Label falseVar = new Label("falseVar"+AbstractOpBool.numInst);
        Label nextVar = new Label("nextVar"+AbstractOpBool.numInst);
        AbstractOpBool.numInst++;
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
