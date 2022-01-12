package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
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
        System.out.println("::Modulo.java:: verifyExpr");
        AbstractExpr rOp = getRightOperand();
        AbstractExpr lOp = getLeftOperand();
        Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
        Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
        if(!type1.isInt() || !type2.isInt())
        {
            throw new ContextualError("Modulo ne supporte que des entiers", getLocation());
        }
        return type1;
    }

    protected int codeGenPrint2(DecacCompiler compiler, AbstractExpr rOp, AbstractExpr lOp) {
        return 1;
    }
    @Override
    protected String getOperatorName() {
        return "%";
    }

}
