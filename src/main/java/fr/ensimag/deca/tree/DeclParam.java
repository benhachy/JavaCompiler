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
    public DeclParam(AbstractIdentifier type,AbstractIdentifier ident){
        this.type= type;
        this.ident=ident;
    }

    @Override
    public void verifyParam(DecacCompiler compiler,Signature signature,EnvironmentExp paramsEnv )
        throws ContextualError 
    {
        Type t = type.verifyType(compiler);
        ident.setType(t);
        ident.setDefinition(compiler.get(type.getName()));
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
        type.decompile(s);
        ident.decompile(s);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        ident.prettyPrint(s, prefix, true);
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        ident.iter(f);
    }
}