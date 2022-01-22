package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import java.io.PrintStream;
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
 *
 * @author gl03
 * @date 01/01/2022
 */
public class InstanceOf extends AbstractExpr {
    public AbstractIdentifier type;
    public AbstractExpr expr;
    public InstanceOf(AbstractIdentifier type,AbstractExpr expr) {
        this.type = type;
        this.expr=expr;
    }
    @Override
    public  Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError{
                //à effacer je l'ai ajouter pour ne pas avoir un pb lors de la compilation delete it and do whatever u wanna do 
            Type returnType = type.verifyType(compiler);
            expr.verifyExpr(compiler, localEnv, currentClass);
            if(!expr.getType().isClassOrNull()){
                throw new ContextualError(expr.getType().getName()+" n'est pas un Objet", expr.getLocation());
            }
            if(!returnType.isClass()){
                throw new ContextualError(returnType.getName()+" n'est pas une classe", type.getLocation());
            }
            if(!verifyCompatibility(localEnv, expr.getType(), returnType)){
                    throw new ContextualError("les deux types "+expr.getType().getName()+" et "+returnType.getName()+" sont incompatibles",getLocation());
            }
            SymbolTable tab = new SymbolTable();
            SymbolTable.Symbol symbol = tab.create("instanceof");
            BooleanType chaine = new BooleanType(symbol);
            setType(chaine);
            return chaine;
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        expr.iter(f);
    }
    protected codeGenInstanceOf(DecacCompiler compiler){
        Label beginningInstanceOf= new Label("instanceOf.Begin");
        Label succesInstanceOf= new Label("instanceOf.succes");
        Label endInstanceOf= new Label("instanceOf.end");

        //B addrB
        compiler.addInstruction(new LOAD(new RegisterOffset(Identifier.posGBIdentificateur.get(type.getName()),Register.GB),Register.getR(1)));
        //C addrC
        compiler.addInstruction(new LOAD(new RegisterOffset(Identifier.posGBIdentificateur.get(expr.getName()),Register.GB),Register.getR(0)));
        compiler.addLabel(beginningInstanceOf);
        compiler.addInstruction(CMP(Register.getR(0),Register.getR(1)));
        compiler.addInstruction(BEQ(succesInstanceOf));
        compiler.addInstruction(new LEA(new RegisterOffset(Identifier.posGBIdentificateur.get(expr.getName()),Register.GB),Register.getR(0)));
        compiler.addInstruction(new LOAD(new RegisterOffset(0,),Register.getR(0)));
        //compiler.addInstruction(CMP(new NullOperand(),Register.getR(1)));
        compiler.addInstruction(BEQ(beginningInstanceOf));
        compiler.addInstruction(BEQ(endInstanceOf));




    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }
    @Override
    public void decompile(IndentPrintStream s) {
        
    }
}