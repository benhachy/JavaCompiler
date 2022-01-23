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
public abstract class AbstractDeclMethod extends Tree {
    public abstract void genCodeMethode(DecacCompiler compiler,DeclClass declClass);
    public abstract void creerEtStockerLabel(DecacCompiler compiler,DeclClass declClass);

    public abstract void verifyMethod(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass,int indice)
            throws ContextualError;

    public abstract void verifyBody(DecacCompiler compiler,
        EnvironmentExp localEnv, ClassDefinition currentClass)
        throws ContextualError;
}