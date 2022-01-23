package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable.Symbol;

/**
 * Class declaration.
 *
 * @author gl03
 * @date 17/01/2022
 */
public abstract class AbstractDeclParam extends Tree {


    public abstract void verifyParam(DecacCompiler compiler,Signature signature,EnvironmentExp paramsEnv )throws ContextualError ;

    public Type getType(){
        return null;
    }
    public Symbol getName(){
        return null;
    }

    public  abstract void codeGenDeclParam(DecacCompiler compiler);

    public abstract void setIndex(int index) ;
    public abstract int  getIndex() ;

    
}