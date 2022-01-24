package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
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
public abstract class AbstractOpCmp extends AbstractBinaryExpr {

    public AbstractOpCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
        Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
            if(!(type1.isInt() && type2.isInt()) && !(type1.isFloat() && type2.isFloat())
                && !(type1.isFloat() && type2.isInt()) && !(type1.isInt() && type2.isFloat()))
            {
                if(!getOperatorName().equals("==") && !getOperatorName().equals("!="))
                {      
                    throw new ContextualError(getOperatorName()+" ne supporte que des float et des Int", getLocation());
                }
                else
                {
                    if(!(type1.isBoolean() && type2.isBoolean()) && !(type1.isNull() && type2.isClass())
                        && !(type1.isClass() && type2.isNull()))
                    { 
                        throw new ContextualError(getOperatorName()+" n'accepte pas "+type1.getName()+" et "+type2.getName(), getLocation());
                    }  
                } 
            } 
        setType(new BooleanType(SymbolTable.creerSymbol(getOperatorName())));
        return getType();
    }


}
