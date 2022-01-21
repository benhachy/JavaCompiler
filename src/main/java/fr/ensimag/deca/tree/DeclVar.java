package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.VariableDefinition;
import fr.ensimag.deca.context.VoidType;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.BSR;
import fr.ensimag.ima.pseudocode.instructions.LEA;
import fr.ensimag.ima.pseudocode.instructions.NEW;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.instructions.TSTO;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

import static org.mockito.ArgumentMatchers.nullable;


/**
 * @author gl03
 * @date 01/01/2022
 */
public class DeclVar extends AbstractDeclVar {

    
    final private AbstractIdentifier type;
    final private AbstractIdentifier varName;
    final private AbstractInitialization initialization;

    public DeclVar(AbstractIdentifier type, AbstractIdentifier varName, AbstractInitialization initialization) {
        Validate.notNull(type);
        Validate.notNull(varName);
        Validate.notNull(initialization);
        this.type = type;
        this.varName = varName;
        this.initialization = initialization;
    }

    @Override
    protected void verifyDeclVar(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        //System.out.println(":: DeclVar :: Verify DeclVar");
        Type t = type.verifyType(compiler);
        initialization.verifyInitialization(compiler,t,localEnv,  currentClass);
        varName.setType(t);
        VariableDefinition var = new VariableDefinition(t,getLocation());
        varName.setDefinition(var);
        try{
            Identifier.identificateurs.put(varName.getName(),Identifier.ordreIdentifier);
            ++Identifier.ordreIdentifier;
            localEnv.declare(varName.getName(),var);
            // if(initialization instanceof Initialization)
            // {
            //     localEnv.setValue(varName.getName(), true);
            // }
            // else{
            //     localEnv.setValue(varName.getName(), false);
            // }
        }
        catch (EnvironmentExp.DoubleDefException e)
        {
            
            throw new ContextualError("la varialble "+ varName.getName()+" est déjà définie ", getLocation());
        }
        catch (DecacInternalError e)
        {
            throw new ContextualError(varName.getName()+" n'est pas une variable ", getLocation());
        }
        
        if(t.sameType(new VoidType(null)))
        {
            throw new ContextualError("Une variable ne peut pas etre de type void ",getLocation());
        }
        
    }

    
    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        varName.decompile(s);
        initialization.decompile(s);
        s.print(";");
    }

    @Override
    protected
    void iterChildren(TreeFunction f) {
        type.iter(f);
        varName.iter(f);
        initialization.iter(f);
    }
    
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        varName.prettyPrint(s, prefix, false);
        initialization.prettyPrint(s, prefix, true);
    }

    @Override
    protected void codeGenDeclVar(DecacCompiler compiler){

        if(type.getType().isClass()){
            
        }else{
            initialization.codeGenInit(compiler);        
            compiler.addInstruction(new STORE(Register.getR(2),new RegisterOffset(Register.getPosGB(),Register.GB)));
        }
        Identifier.posGBIdentificateur.put(varName.getName(),Register.getPosGB());
        Register.updatePosGB();
    }

    @Override
    public void codeGenDeclvarMethode(DecacCompiler compiler){
        if(type.getType().isClass()){
            // to do
        }else{
            initialization.codeGenInit(compiler);        
            compiler.addInstruction(new STORE(Register.getR(2),new RegisterOffset(Register.getPosLB(),Register.LB)));
        }
        Identifier.posLBIdentificateur.put(varName.getName(),Register.getPosLB());
        Register.updatePosLB();
    }
}
