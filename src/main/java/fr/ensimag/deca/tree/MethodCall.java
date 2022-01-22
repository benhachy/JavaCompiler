package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import java.time.format.SignStyle;

import org.apache.commons.lang.Validate;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.BSR;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
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
        //name : l'expression à laquelle on applique la méthode MethodName et qui prend en argument lisExpression
        name.verifyExpr(compiler, localEnv, currentClass);
        if(!name.getType().isClassOrNull()){
            throw new ContextualError(methodName.getName()+" n'est applicable que sur des objets", getLocation());
        }

        ClassType classe = (ClassType) name.getType();
        //EnvironmentExp envClass = compiler.getEnv(classe.getName());
        MethodDefinition defMethod=methodName.verifyExistence(compiler,classe);
        methodName.setDefinition(defMethod);
        Signature sig = defMethod.getSignature();
        listExpression.verifySignature(compiler,localEnv,currentClass,sig);
        setType(defMethod.getType());
        return getType();
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        name.iter(f); 
        methodName.iter(f); 
        listExpression.iter(f);    
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
    public void codeGenInst(DecacCompiler compiler){
        for (AbstractExpr exp : listExpression.getList()) {
            exp.codeGenInst(compiler);
            compiler.addInstruction(new PUSH(Register.getR(2)));
        }
        compiler.addInstruction(new BSR(new Label("code."+name.getType().getName().getName()+"."+methodName.getName().getName())));
    }    
}