package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;

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
        //System.out.println("::Assign.java:: verifyExpr");
        AbstractExpr rvalue = getRightOperand();
        AbstractLValue lvalue = getLeftOperand();
        if(lvalue instanceof Identifier)
        {
            //System.out.println("========>>>  "+((AbstractIdentifier)lvalue).getName());
            //((AbstractIdentifier)lvalue).getName().setOrdre();
            localEnv.setValue(((AbstractIdentifier)lvalue).getName(), true);
        }
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
        //System.out.println("::Assign.java:: codeGenInst");
        AbstractExpr rvalue = getRightOperand();
        rvalue.codeGenInst(compiler);
        compiler.addInstruction(new PUSH(Register.getR(1)));
    }
    @Override
    protected String getOperatorName() {
        return "=";
    }

}
