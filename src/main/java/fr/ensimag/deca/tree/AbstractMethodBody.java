package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;

/**
 * Class declaration.
 *
 * @author gl03
 * @date 17/01/2022
 */
public abstract class AbstractMethodBody extends Tree {


    public abstract void verifyMethodBody(DecacCompiler compiler,EnvironmentExp localEnv, 
    EnvironmentExp parametres, ClassDefinition currentClass, Type expectedReturn) throws ContextualError;
    public abstract void codeGenMethodBody(DecacCompiler compiler);
}