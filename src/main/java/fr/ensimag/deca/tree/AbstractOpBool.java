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
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

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
            return new BooleanType(SymbolTable.creerSymbol("OpBool"));
    }

}
