package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclMethod extends AbstractDeclMethod {
    AbstractIdentifier type;
    AbstractIdentifier name;
    ListDeclParam   paramDecl;
    AbstractMethodBody  methodBody;
    public DeclMethod(AbstractIdentifier type,AbstractIdentifier name,ListDeclParam   paramDecl,AbstractMethodBody methodBody){
        this.type= type;
        this.name=name;
        this.paramDecl= paramDecl;
        this.methodBody=methodBody;
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


    @Override
    public void verifyMethod(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError{
        System.out.println(" type "+type.getName()+" name "+name.getName());
        Type expectedReturn = compiler.get(type.getName()).getType();
        Signature signature = new Signature();
        EnvironmentExp paramsEnv = paramDecl.verifyListParam(compiler,signature);
        MethodDefinition method = new MethodDefinition(expectedReturn,getLocation(),signature,0);
        try{
            localEnv.declare(name.getName(),method);
        }
        catch (EnvironmentExp.DoubleDefException e)
        {
            
            throw new ContextualError("la méthode "+ name.getName()+" est déjà définie", getLocation());
        }
        
        methodBody.verifyMethodBody(compiler,localEnv,paramsEnv,currentClass,expectedReturn);
    }

}
