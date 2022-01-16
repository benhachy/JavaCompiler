package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.OPP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;


/**
 * @author gl03
 * @date 01/01/2022
 */
public class UnaryMinus extends AbstractUnaryExpr {

    public UnaryMinus(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        //System.out.println(":: UnaryMinus :: verifyExpr");
        AbstractExpr unaryOperand = getOperand();
        Type typeMinus= unaryOperand.verifyExpr(compiler, localEnv, currentClass);
        if ( !typeMinus.isFloat() && !typeMinus.isInt() ){
            throw new ContextualError("Minus ne supporte pas  "+typeMinus.getName()+"", getLocation());
        }
        setType(typeMinus);
        return typeMinus;
    }
    @Override
    protected void codeGenPrint(DecacCompiler compiler) {
        codeGenInst(compiler);
        compiler.addInstruction(new LOAD(Register.getR(2),Register.getR(1)));
    }
    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        AbstractExpr unaryOperand = getOperand();
        unaryOperand.codeGenExpr(compiler, 2);
        compiler.addInstruction(new OPP(Register.getR(2),Register.getR(2)));
    }

    @Override
    protected String getOperatorName() {
        return "-";
    }

}
