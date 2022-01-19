package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.BooleanType;


/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class MethodCall extends AbstractExpr {
    AbstractExpr name ;
    AbstractIdentifier methodName ;
    ListExpr listExpression;
    public MethodCall(AbstractExpr name ,
    AbstractIdentifier methodName ,
    ListExpr listExpression) {
        this.name = name;
        this.methodName = methodName;
        this.listExpression = listExpression;
    }
    public  Type verifyExpr(DecacCompiler compiler,
    EnvironmentExp localEnv, ClassDefinition currentClass)
    throws ContextualError{
         //à effacer je l'ai ajouter pour ne pas avoir un pb lors de la compilation delete it and do whatever u wanna do 
         SymbolTable tab = new SymbolTable();
         SymbolTable.Symbol symbol = tab.create("boolean");
         BooleanType chaine = new BooleanType(symbol);
         setType(chaine);
         return chaine;
    }
    @Override
    protected void iterChildren(TreeFunction f) {
    throw new UnsupportedOperationException("Not yet supported");
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        name.prettyPrint(s, prefix, false);
        methodName.prettyPrint(s, prefix, false);
        listExpression.prettyPrint(s, prefix, false);
    }
    @Override
    public void decompile(IndentPrintStream s) {

    }
    
}