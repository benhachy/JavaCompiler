package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.FieldDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.context.VariableDefinition;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import java.io.PrintStream;
import java.util.HashMap;
import org.apache.commons.lang.Validate;


import static org.mockito.ArgumentMatchers.nullable;



/**
 * Deca Identifier
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Identifier extends AbstractIdentifier {
    
    @Override
    protected void checkDecoration() {
        if (getDefinition() == null) {
            throw new DecacInternalError("Identifier " + this.getName() + " has no attached Definition");
        }
    }

    @Override
    public Definition getDefinition() {
        return definition;
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * ClassDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a class definition.
     */
    @Override
    public ClassDefinition getClassDefinition() {
        try {
            return (ClassDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a class identifier, you can't call getClassDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * MethodDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a method definition.
     */
    @Override
    public MethodDefinition getMethodDefinition() {
        try {
            return (MethodDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a method identifier, you can't call getMethodDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * FieldDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a field definition.
     */
    @Override
    public FieldDefinition getFieldDefinition() {
        try {
            return (FieldDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a field identifier, you can't call getFieldDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * VariableDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a field definition.
     */
    @Override
    public VariableDefinition getVariableDefinition() {
        try {
            return (VariableDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a variable identifier, you can't call getVariableDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a ExpDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a field definition.
     */
    @Override
    public ExpDefinition getExpDefinition() {
        try {
            return (ExpDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a Exp identifier, you can't call getExpDefinition on it");
        }
    }

    @Override
    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    @Override
    public Symbol getName() {
        return name;
    }

    private Symbol name;
    private boolean estInitialisee;

    public void setBool(boolean val)
    {
        this.estInitialisee = val;
    }
    public  boolean getBool()
    {
        return estInitialisee;
    }

    public Identifier(Symbol name) {
        Validate.notNull(name);
        this.name = name;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        ExpDefinition def = localEnv.get(getName());
        if(def==null)
        {
            throw new ContextualError("la variable "+getName()+" n'est pas déclarée",getLocation());
        }
        // if(!localEnv.getValue(getName()))
        // {
        //     throw new ContextualError("la variable "+getName()+" n'est pas initialisée",getLocation());
        // }
        this.setDefinition(def);
        setType(def.getType());
        return this.getType();
    }

    @Override
    public ClassDefinition verifyIdentifier( DecacCompiler compiler,ClassType c,TypeDefinition definition)throws ContextualError {
        if(compiler.get(getName())==null)
        {
            throw new ContextualError("la classe "+getName()+" n'est pas déclarée",getLocation());
        }
        setType(c);
        setDefinition((new ClassDefinition(c,getLocation(),(ClassDefinition)definition)));
        return getClassDefinition();
    }

    /**
     * Implements non-terminal "type" of [SyntaxeContextuelle] in the 3 passes
     * @param compiler contains "env_types" attribute
     */
    @Override
    public Type verifyType(DecacCompiler compiler) throws ContextualError {
        if(compiler.getDefinition(this.getName()) == null)
        {
            throw new ContextualError(this.getName()+" n'est pas défini",getLocation());
        }
        setDefinition(compiler.getDefinition(getName()));

        Type type =getDefinition().getType();
        if(type.isVoid() || type.isString())
        {
            throw new ContextualError(type.getName()+" n'est pas un type pour initialiser",getLocation());
        }
        setType(type);
        return type;
    }
    

    public MethodDefinition verifyExistence(DecacCompiler compiler, ClassType classe)throws ContextualError{
        Symbol identifier = getName();
        EnvironmentExp envClass;
        ClassDefinition def = compiler.getClass(classe.getName());
        while(def != null){
            classe = def.getType();
            envClass = compiler.getEnv(classe.getName());
            if(envClass.get(identifier) != null){
                if(!envClass.get(identifier).isMethod()){
                    throw new ContextualError(identifier.getName()+" n'est pas une méthode",getLocation());
                }
                MethodDefinition methodDef = (MethodDefinition)envClass.get(identifier);
                setDefinition(methodDef);
                setType(methodDef.getType());
                return methodDef;
            }
            def = def.getSuperClass();
        }
        throw new ContextualError(identifier.getName()+" n'est pas une méthode définie",getLocation());
    }


    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        
        compiler.addInstruction(new LOAD(new RegisterOffset(Identifier.identificateurs.get(getName())+3,Register.GB),Register.getR(2) ));
    }
    @Override
    public void codeGenExpr(DecacCompiler compiler,int n) {
        compiler.addInstruction(new LOAD(new RegisterOffset(Identifier.identificateurs.get(getName())+3,Register.GB),Register.getR(n) ));
        
    }
    @Override
    public void  codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) {
        compiler.addInstruction(new LOAD(new RegisterOffset(Identifier.identificateurs.get(getName())+3,Register.GB),Register.getR(0) ));
        if(b){
            compiler.addInstruction(new CMP( new ImmediateInteger(0) ,Register.getR(0)));
            compiler.addInstruction(new BNE(E));

        }
        else{
            compiler.addInstruction(new CMP(new ImmediateInteger(0),Register.getR(0)));
            compiler.addInstruction(new BEQ(E));

        }
    }
    @Override
    protected void codeGenPrint(DecacCompiler compiler){
        compiler.addInstruction(new LOAD(new RegisterOffset(Identifier.identificateurs.get(getName())+3,Register.GB),Register.getR(1) ));
    }

    @Override
    public  void codeGenAssign(DecacCompiler compiler){
        compiler.addInstruction(new STORE( Register.getR(2),new RegisterOffset(Identifier.identificateurs.get(getName())+3,Register.GB) ));
    }


    private Definition definition;
    public static HashMap<Symbol,Integer> identificateurs = new HashMap<Symbol,Integer>();
    public static HashMap<Symbol,Integer> posGBIdentificateur = new HashMap<Symbol,Integer>();
    public static HashMap<Symbol,Integer> posLBIdentificateur = new HashMap<Symbol,Integer>();
    public static int ordreIdentifier;

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(name.toString());
    }

    @Override
    String prettyPrintNode() {
        return "Identifier (" + getName() + ")";
    }

    @Override
    protected void prettyPrintType(PrintStream s, String prefix) {
        Definition d = getDefinition();
        if (d != null) {
            s.print(prefix);
            s.print("definition: ");
            s.print(d);
            s.println();
        }
    }

}
