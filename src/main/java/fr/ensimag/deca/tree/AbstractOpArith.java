package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
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
        //System.out.println("::AbstractOpArith.java:: verifyExpr");
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
            setType(type1);
            return type1;
        }
        setType(type2);
        return type2;
    }
    @Override
    protected void codeGenPrint(DecacCompiler compiler)
    {
        System.out.println("::AbstractOpArith.java:: codeGenPrint");
        // AbstractExpr rOp = getRightOperand();
        // AbstractExpr lOp = getLeftOperand();



        // rOp.codeGenPrintversiontest(compiler,rOp,Register.getR(4));
        // lOp.codeGenPrintversiontest2(compiler,)
        // //codeGenPrint2(compiler,rOp,lOp);
        // //R1 contient 4
        // //compiler.addInstruction(new ADD(Register.getR(1),Register.getR(0)));
        // //4 en r0 
        // lOp.codeGenPrint(compiler);
        // //R0 contiet 4
        
        // //compiler.addInstruction(new LOAD(Register.getR(0),Register.getR(1) ));
        // //R1 contient 4
        
    }
    // protected void codeGenPrintversiontest2(DecacCompiler compiler,AbstractExpr rightOperand, AbstractExpr lOp){
    //     if ( )
    //     codeGenPrintversiontest2(compiler,lOp.getLeftOperand(); lOp.getRightOperand());
    //     compiler.addInstruction(new ADD(rightOperand.getValue(),Register.getR(1)));
    //     return 1;

    // }
    protected abstract int codeGenPrint2(DecacCompiler compiler, AbstractExpr rOp, AbstractExpr lOp) ;
    
    
}
