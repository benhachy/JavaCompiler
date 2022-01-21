package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.FloatType;
import fr.ensimag.deca.context.IntType;
import fr.ensimag.deca.context.NullType;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;

/**
 * Absence of initialization (e.g. "int x;" as opposed to "int x =
 * 42;").
 *
 * @author gl03
 * @date 01/01/2022
 */
public class NoInitialization extends AbstractInitialization {

    Type type;
    AbstractExpr defaultValue;

    @Override
    protected void verifyInitialization(DecacCompiler compiler, Type t,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
            if(t.isBoolean())
            {
                defaultValue = new BooleanLiteral(false);
                defaultValue.verifyRValue(compiler,localEnv,currentClass,t);
                type = t;
            }
            else if(t.isInt())
            {
                defaultValue = new IntLiteral(0);
                defaultValue.verifyRValue(compiler,localEnv,currentClass,t);
                type = t;
            }
            else if(t.isFloat())
            {
                defaultValue = new FloatLiteral(0);
                defaultValue.verifyRValue(compiler,localEnv,currentClass,t);
                type = t;
            }
            else if(t.isClassOrNull())
            {
                defaultValue = new Null();
                defaultValue.verifyRValue(compiler,localEnv,currentClass,t);
                type = t;
            }
            // else{
            //     throw new ContextualError("le type n'est pas défini ",null);
            // }
    }


    /**
     * Node contains no real information, nothing to check.
     */
    @Override
    protected void checkLocation() {
        // nothing
    }

    @Override
    public void decompile(IndentPrintStream s) {
        // nothing
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    public void codeGenInit(DecacCompiler compiler)
    {
        defaultValue.codeGenInst(compiler);
    }
}
