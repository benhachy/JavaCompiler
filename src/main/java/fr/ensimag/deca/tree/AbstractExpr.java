package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;

import java.io.PrintStream;


import org.apache.commons.lang.Validate;

/**
 * Expression, i.e. anything that has a value.
 *
 * @author gl03
 * @date 01/01/2022
 */
public abstract class AbstractExpr extends AbstractInst {
    /**
     * @return true if the expression does not correspond to any concrete token
     * in the source code (and should be decompiled to the empty string).
     */
    boolean isImplicit() {
        return false;
    }

    public boolean isIdentifier() {
        return false;
    }
    /**
     * Get the type decoration associated to this expression (i.e. the type computed by contextual verification).
     */
    public Type getType() {
        return type;
    }

    protected void setType(Type type) {
        Validate.notNull(type);
        this.type = type;
    }
    private Type type;
    private Boolean initiate;

    protected void SetValueBool(boolean val)
    {
        initiate = val;
    }
    protected boolean getValueBool()
    {
        return initiate;
    }
    @Override
    protected void checkDecoration() {
        if (getType() == null) {

            throw new DecacInternalError("Expression " + decompile() + " has no Type decoration");
        }
    }

    /**
     * Verify the expression for contextual error.
     * 
     * implements non-terminals "expr" and "lvalue" 
     *    of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler  (contains the "env_types" attribute)
     * @param localEnv
     *            Environment in which the expression should be checked
     *            (corresponds to the "env_exp" attribute)
     * @param currentClass
     *            Definition of the class containing the expression
     *            (corresponds to the "class" attribute)
     *             is null in the main bloc.
     * @return the Type of the expression
     *            (corresponds to the "type" attribute)
     */
    public abstract Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError;

    /**
     * Verify the expression in right hand-side of (implicit) assignments 
     * 
     * implements non-terminal "rvalue" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler  contains the "env_types" attribute
     * @param localEnv corresponds to the "env_exp" attribute
     * @param currentClass corresponds to the "class" attribute
     * @param expectedType corresponds to the "type1" attribute            
     * @return this with an additional ConvFloat if needed...
     */
    
    public AbstractExpr verifyRValue(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass, 
            Type expectedType)
            throws ContextualError {
        //System.out.println("::AbstractExpr.java :: verifyRValue");
        Type expression = this.verifyExpr(compiler, localEnv, currentClass);
        if(!verifyCompatibility(localEnv, expectedType, expression))
        {
            throw new ContextualError("Les deux types "+expectedType.toString()+
            " et "+expression.toString()+" sont incompatibles ", getLocation());
        }
        this.setType(expression);
        return this;
    }
    protected boolean verifyCompatibility(EnvironmentExp localEnv,Type type1,Type type2)
    {
        if(type1.isFloat() && type2.isInt())
        {
            return true;
        }
        else if(type1.isNull() || type2.isNull())
        {
            return true;
        }
        else if(!type1.isClass() || !type2.isClass())
        {
            if(type2.sameType(type1)){
                return true;
            }
            return false;
        }
        
        else if( type1.isClass() && type2.isClass()){
            if(type1.getName().equals(type2.getName())){
                return true;
            }
            return ((ClassType)type2).isSubClassOf((ClassType)type1);
        }
        return false;
    }
    
    
    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        //System.out.println("::AbstractExpr.java:: veridyInst");
        Type retour = this.verifyExpr(compiler, localEnv, currentClass);
        // if(!verifyCompatibility(localEnv, retour, returnType)){
        //     throw new ContextualError("les deux types "+retour.getName()+" et "+returnType.getName()+" sont incompatibles",getLocation());
        // }
    }

    
    /**
     * Verify the expression as a condition, i.e. check that the type is
     * boolean.
     *
     * @param localEnv
     *            Environment in which the condition should be checked.
     * @param currentClass
     *            Definition of the class containing the expression, or null in
     *            the main program.
     */
    void verifyCondition(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        //System.out.println("::AbstractExpr.java::verifyCondition ");
        Type condition = verifyExpr(compiler, localEnv, currentClass);
        if(!condition.isBoolean())
        {
            throw new ContextualError("La condition doit etre de type Boolean", getLocation());
        }
    }

    /**
     * Generate code to print the expression
     *
     * @param compiler
     */
    protected void codeGenPrint(DecacCompiler compiler) {
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        //throw new UnsupportedOperationException("not yet implemented");
    }
    

    @Override
    protected void decompileInst(IndentPrintStream s) {
        decompile(s);
        s.print(";");
    }

    @Override
    protected void prettyPrintType(PrintStream s, String prefix) {
        Type t = getType();
        if (t != null) {
            s.print(prefix);
            s.print("type: ");
            s.print(t);
            s.println();
        }
    }
    public boolean isLiteral()
    {
        return false;
    }

    public void codeGenExpr(DecacCompiler compiler,int n) {
        throw new UnsupportedOperationException("not yet implemented");
        
    }

    public void codeGenOp(DecacCompiler compiler, GPRegister leftOperand, GPRegister rightOperand,int n) {
        return ;
    }

    public void codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        return ;
    }

    public void codeGenCode(DecacCompiler compiler, DVal C,boolean b,Label E) {
        return ;
    }
}
