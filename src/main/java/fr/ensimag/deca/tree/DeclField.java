package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.FieldDefinition;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.VoidType;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tree.Visibility;
import fr.ensimag.ima.pseudocode.NullOperand;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclField extends AbstractDeclField {
    final private Visibility visibility;
    final private AbstractIdentifier type;
    final private AbstractIdentifier name;
    final private AbstractInitialization initialization;
    public DeclField(Visibility visibility,AbstractIdentifier type,AbstractIdentifier  name,AbstractInitialization  initiate){

        this.visibility= visibility;
        this.type=type;
        this.name= name;
        this.initialization=initiate;
    }

    @Override
    public AbstractIdentifier getType(){
        return type;
    }
    @Override
    public AbstractIdentifier getName(){
        return name;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        if (this.visibility.equals(Visibility.PROTECTED)){
            s.print("protected ");
        } 
        type.decompile(s);
        s.print(" ");
        name.decompile(s);
        s.print(" ");
        initialization.decompile(s);
        s.print(";");
    }

    @Override
    public void verifyFeild(DecacCompiler compiler,EnvironmentExp localEnv, ClassDefinition superClass,ClassDefinition currentClass,int indice)
    throws ContextualError {
        Type t = type.verifyType(compiler);
        initialization.verifyInitialization(compiler,t,localEnv,  currentClass);
        name.setType(t);
        FieldDefinition field = new FieldDefinition(t, getLocation(), visibility,currentClass,indice);
        name.setDefinition(field);
        try{
            localEnv.declare(name.getName(),field);
        }
        catch (EnvironmentExp.DoubleDefException e)
        {
            
            throw new ContextualError("l'attribut'"+ name.getName()+" est déjà définie ", getLocation());
        }
        catch (DecacInternalError e)
        {
            throw new ContextualError(name.getName()+" n'est pas un attribut ", getLocation());
        }
        
        if(t.sameType(new VoidType(null)))
        {
            throw new ContextualError("Un attribut ne peut pas etre de type void ",getLocation());
        }
        
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s,prefix,false);
        name.prettyPrint(s,prefix,false);
        initialization.prettyPrint(s, prefix, false);
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        name.iter(f);
        initialization.iter(f);
    }
    @Override
    protected void codeGenFeild(DecacCompiler compiler){
        initialization.codeGenInitFeilds(compiler);
        compiler.addInstruction(new STORE(Register.getR(0),new RegisterOffset(name.getFieldDefinition().getIndex()+1,Register.getR(1))));
        this.getName().getType();
        int pos = 1 ;
        Identifier.addVariableAddress(this.getName().getName(), pos, Register.getR(1));
        pos++;
    }
}