package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
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

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        //System.out.println("::AbstractOpArith.java:: verifyExpr");
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
}
