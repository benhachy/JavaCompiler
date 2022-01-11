package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;

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
        System.out.println(":: UnaryMinus :: verifyExpr");
        AbstractExpr unaryOperand = getOperand();
        Type typeMinus= unaryOperand.verifyExpr(compiler, localEnv, currentClass);
        if ( !typeMinus.isFloat() && !typeMinus.isInt() ){
            throw new ContextualError("Minus ne supporte pas  "+typeMinus.getName()+"", getLocation());
        }
        return typeMinus;
    }


    @Override
    protected String getOperatorName() {
        return "-";
    }

}
