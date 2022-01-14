package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

import java.io.PrintStream;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class BooleanLiteral extends AbstractExpr {

    private boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
            SymbolTable tab = new SymbolTable();
            SymbolTable.Symbol symbol = tab.create("boolean");
            BooleanType chaine = new BooleanType(symbol);
            setType(chaine);
            return chaine;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print(Boolean.toString(value));
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    String prettyPrintNode() {
        return "BooleanLiteral (" + value + ")";
    }

    @Override
    public void codeGenOpBool(DecacCompiler compiler,GPRegister leftOperand, GPRegister rightOperand,boolean b,Label E,Label EFin,int n) 
    {
        System.out.println("::BooleanLIteral.java:: codeGenOpBool");
        System.out.println(":: i am "+getValue()+"and the bool is "+b+E.toString());
        if(this.getValue() && b || !this.getValue() && !b )
        {   
            System.out.println(":: i will write a branch to "+E.toString());
            //compiler.addInstruction(new LOAD(new ImmediateInteger(1),Register.getR(n) ));
            compiler.addInstruction(new BRA(E));
        }
    }
    @Override
    public void codeGenExpr(DecacCompiler compiler,int n)
    {
        if(getValue())
        {
            compiler.addInstruction(new LOAD(new ImmediateInteger(1),Register.getR(n) ));
        }
        else{
            compiler.addInstruction(new LOAD(new ImmediateInteger(0),Register.getR(n) ));
        }
        
        
    }

}
