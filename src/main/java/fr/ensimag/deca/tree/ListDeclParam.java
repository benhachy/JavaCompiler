package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.util.List;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class ListDeclParam extends TreeList<AbstractDeclParam> {
    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractDeclParam e : getList()){
            e.decompile(s);
        }
    }

    public EnvironmentExp verifyListParam(DecacCompiler compiler,Signature signature)
    throws ContextualError 
    {
        EnvironmentExp paramsEnv = new EnvironmentExp(null);
        for(AbstractDeclParam p :getList())
        {
            p.verifyParam(compiler,signature,paramsEnv);
        }
        return paramsEnv;
    }
    
}