package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Conversion of an int into a float. Used for implicit conversions.
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class ConvFloat extends AbstractUnaryExpr {
    public ConvFloat(AbstractExpr operand) {
        super(operand);
    }

    // @Override
    // public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
    //         ClassDefinition currentClass) throws ContextualError  {
    //     //System.out.println(":: UnaryMinus :: verifyExpr");
    //     AbstractExpr unaryOperand = getOperand();
    //     Type typeInt= unaryOperand.verifyExpr(compiler, localEnv, currentClass);
    //     if ( !typeInt.isInt() && !typeInt.isFloat()){
    //         throw new ContextualError("Nous ne pouvons pas convertir "+typeInt.getType()+" en float",getLocation());
    //     }
    //     setType(new FloatType(SymbolTable.));
    //     return typeMinus;
    // }


    @Override
    protected String getOperatorName() {
        return "/* conv float */";
    }

}
