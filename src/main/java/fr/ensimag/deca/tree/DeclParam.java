package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ParamDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclParam extends AbstractDeclParam {
    AbstractIdentifier type;
    AbstractIdentifier ident;
    public DeclParam(AbstractIdentifier type,AbstractIdentifier name){
        this.type= type;
        this.ident=name;
    }


    @Override
    public void verifyParam(DecacCompiler compiler,Signature signature,EnvironmentExp paramsEnv )
        throws ContextualError 
    {
        Type t = type.verifyType(compiler);
        ident.setType(t);
        try{
            paramsEnv.declare(ident.getName(),new ParamDefinition(t,getLocation()));
        }
        catch (EnvironmentExp.DoubleDefException e)
        {
            
            throw new ContextualError("le parametre "+ident.getName()+" est deja déclaré ", getLocation());
        }
        
        signature.add(t);
    }


    @Override
    public Type getType(){
        return ident.getType();
    }
    @Override
    public Symbol getName(){
        return ident.getName();
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }



    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        throw new UnsupportedOperationException("Not yet supported");
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        throw new UnsupportedOperationException("Not yet supported");
    }
}