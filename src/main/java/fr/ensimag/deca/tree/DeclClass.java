package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclClass extends AbstractDeclClass {
    AbstractIdentifier identifier;
    AbstractIdentifier classExtension;
    ListDeclField   feildDecl;
    ListDeclMethod  methodDecl;


    public DeclClass(AbstractIdentifier identifier,AbstractIdentifier classExtension,ListDeclField  feildDecl,ListDeclMethod  methodDecl){
        this.identifier= identifier;
        this.classExtension=classExtension;
        this.feildDecl= feildDecl;
        this.methodDecl=methodDecl;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }

    @Override
    protected void verifyClass(DecacCompiler compiler) throws ContextualError {
        System.out.println("DeclClass.java :: verifyClass");
        
        ClassType c = new ClassType(identifier.getName(),getLocation(),null);
        TypeDefinition superClass = compiler.get(classExtension.getName());
        if(superClass == null)
        {
            throw new ContextualError("la super classe "+ classExtension.getName()  +" n'est déjà définie", getLocation());
        }
        
        try{
            classExtension.setDefinition(superClass);
            identifier.setDefinition((new ClassDefinition(c,getLocation(),classExtension.getClassDefinition())));
            compiler.declare(identifier.getName(), identifier.getClassDefinition());
        }
        catch( DoubleDefException e)
        {
            throw new ContextualError("la classe "+ identifier.getName()  +" est déjà définie", getLocation());
        }
        catch( DecacInternalError e)
        {
            throw new ContextualError(classExtension.getName()  +" n'est pas une class", getLocation());
        }
        EnvironmentExp envExpF = new EnvironmentExp(null);
        for(AbstractDeclField f : feildDecl.getList())
        {
            f.verifyFeild(compiler,envExpF,classExtension.getClassDefinition(),identifier.getClassDefinition());
        }
        for(AbstractDeclMethod f : methodDecl.getList())
        {
            f.verifyMethod(compiler,envExpF,classExtension.getClassDefinition());
        }
        compiler.setEvn(identifier.getName(),envExpF);
    }

    @Override
    protected void verifyClassMembers(DecacCompiler compiler)
            throws ContextualError {
        System.out.println("ListDeclClass.java :: verifyClassMembers");
        throw new UnsupportedOperationException("not yet implemented");
    }
    
    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
        System.out.println("ListDeclClass.java :: verifyClassBody");
        throw new UnsupportedOperationException("not yet implemented");
    }


    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        identifier.prettyPrint(s, prefix, false);
        classExtension.prettyPrint(s, prefix, false);
        feildDecl.prettyPrintChildren(s,prefix);
        //throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        throw new UnsupportedOperationException("Not yet supported");
    }

}
