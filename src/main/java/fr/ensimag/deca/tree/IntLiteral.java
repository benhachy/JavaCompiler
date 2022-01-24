package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.IntType;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.*;
import java.io.PrintStream;

/**
 * Integer literal
 *
 * @author gl03
 * @date 01/01/2022
 */
public class IntLiteral extends AbstractExpr {
    public int getValue() {
        return value;
    }

    private int value;

    public IntLiteral(int value) {
        this.value = value;
    }

    @Override
    public boolean isLiteral()
    {
        return true;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
            SymbolTable tab = new SymbolTable();
            SymbolTable.Symbol symbol = tab.create(value+"");
            IntType chaine = new IntType(symbol);
            setType(chaine);
            return chaine;
    }


    @Override
    String prettyPrintNode() {
        return "Int (" + getValue() + ")";
    }
    @Override
    protected void codeGenPrint(DecacCompiler compiler){
        compiler.addInstruction(new LOAD(new ImmediateInteger(getValue()),Register.getR(1) ));
    }
    @Override
    public void codeGenExpr(DecacCompiler compiler,int n)
    {
        compiler.addInstruction(new LOAD(new ImmediateInteger(getValue()),Register.getR(n) ));
        
    }
    @Override
    protected void codeGenInst(DecacCompiler compiler){
        compiler.addInstruction(new LOAD(new ImmediateInteger(getValue()),Register.getR(2) ));
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(Integer.toString(value));
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

}
