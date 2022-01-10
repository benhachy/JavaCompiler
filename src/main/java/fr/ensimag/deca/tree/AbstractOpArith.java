package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Arithmetic binary operations (+, -, /, ...)
 * 
 * @author gl03
 * @date 01/01/2022
 */
public abstract class AbstractOpArith extends AbstractBinaryExpr {

    public AbstractOpArith(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        System.out.println("::AbstractOpArith.java:: verifyExpr");
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
        Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
        if(!(type1.isInt() && type2.isInt()) && !(type1.isFloat() && type2.isFloat())
        && !(type1.isFloat() && type2.isInt()) && !(type1.isInt() && type2.isFloat()))
        {
            throw new ContextualError("Les deux types "+type1.getName()+" et "+type2.getName()+
            " ne sont pas compatibles pour une opération arithmetique", getLocation());
        }
        if(type1.isFloat())
        {
            return type1;
        }
        return type2;
    }
}
