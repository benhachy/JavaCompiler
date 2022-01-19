package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class MethodAsm extends AbstractMethodBody {
    private String multLineString;
    public MethodAsm( String multLineString){
        Validate.notNull(multLineString);
        this.multLineString = multLineString;
    }
    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        //nothing
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    public void verifyMethodBody(DecacCompiler compiler,EnvironmentExp localEnv, 
    EnvironmentExp parametres, ClassDefinition currentClass, Type expectedReturn) throws ContextualError{
            throw new UnsupportedOperationException("Not yet implemented");
        }
}