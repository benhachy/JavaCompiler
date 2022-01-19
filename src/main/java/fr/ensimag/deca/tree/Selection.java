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
import fr.ensimag.deca.context.BooleanType;




/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Selection extends AbstractLValue {
    public AbstractIdentifier type;
    public AbstractExpr expr;
    public Selection(AbstractIdentifier type,AbstractExpr expr) {
        this.type = type;
        this.expr=expr;
    }
    public  void codeGenAssign(DecacCompiler compiler){

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
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        expr.iter(f);
        type.iter(f);
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    // leaf node => nothing to do
    expr.prettyPrint(s, prefix, false);
    type.prettyPrint(s, prefix, false);
    }
}