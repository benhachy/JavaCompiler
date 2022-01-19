package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;

/**
 * Class declaration.
 *
 * @author gl03
 * @date 17/01/2022
 */
public abstract class AbstractDeclMethod extends Tree {
    public abstract void genCodeMethode(DecacCompiler compiler);
    public abstract void creerEtStockerLabel(DecacCompiler compiler);
}