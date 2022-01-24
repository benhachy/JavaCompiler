package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;

/**
 * Assignment, i.e. lvalue = expr.
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Assign extends AbstractBinaryExpr {

    @Override
    public AbstractLValue getLeftOperand() {
        // The cast succeeds by construction, as the leftOperand has been set
        // as an AbstractLValue by the constructor.
        return (AbstractLValue)super.getLeftOperand();
    }

    public Assign(AbstractLValue leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        AbstractExpr rvalue = getRightOperand();
        AbstractLValue lvalue = getLeftOperand();
        
        Type variable = lvalue.verifyExpr(compiler, localEnv, currentClass);
        rvalue.verifyRValue(compiler, localEnv, currentClass, variable);
        if(!verifyCompatibility(localEnv, variable, rvalue.getType()))
        {
            throw new ContextualError("L'affectation est impossible car les deux types "+
            variable.getName()+" et "+rvalue.getType().getName()+" ne sont pas compatibles", getLocation());
        }
        setType(variable);
        return variable;
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        System.out.println("::Assign.java:: codeGenInst");
        AbstractExpr rvalue = getRightOperand();
        AbstractLValue lvalue = getLeftOperand();
        rvalue.codeGenInst(compiler);
        System.out.print("********************************************"+rvalue.getClass().toString());
        if( rvalue instanceof MethodCall){
            compiler.addInstruction(new PUSH(Register.getR(2)));
            compiler.addInstruction(new LOAD(Register.getR(0),Register.getR(2)));
        }
        if(rvalue.getType().isInt() && lvalue.getType().isFloat())
        {
            compiler.addInstruction(new FLOAT(Register.getR(2),Register.getR(2)));
        }
        //compiler.addInstruction(new PUSH(Register.getR(2)));
        lvalue.codeGenAssign(compiler,2);
        if( rvalue instanceof MethodCall){
        compiler.addInstruction(new POP(Register.getR(2)));
        }
    }
    @Override
    protected String getOperatorName() {
        return "=";
    }

}
