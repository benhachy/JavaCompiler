package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Class declaration.
 *
 * @author gl03
 * @date 17/01/2022
 */

public abstract class AbstractDeclField extends Tree {

    public abstract void verifyFeild(DecacCompiler compiler, EnvironmentExp localEnv,ClassDefinition superClass,ClassDefinition classe,int indice)
    throws ContextualError ;
    public abstract AbstractIdentifier getType();
    public abstract AbstractIdentifier getName();
    protected abstract void codeGenFeild(DecacCompiler compiler);


}