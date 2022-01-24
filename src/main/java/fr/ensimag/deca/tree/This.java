package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;

import java.io.PrintStream;



/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class This extends AbstractExpr {
    @Override
    public  Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError{
            SymbolTable.Symbol classe0 = SymbolTable.creerSymbol("0");
            if(currentClass == null){
                throw new ContextualError(" This est défini que dans des classes",getLocation());
            }
            if( currentClass.getType().getName().equals(classe0)){
                throw new ContextualError(" This est défini que dans des classes",getLocation());
            }
            ClassType type = new ClassType(currentClass.getType().getName(),getLocation(), currentClass.getSuperClass());
            setType(type);
            return type;
            }
    @Override
    protected void iterChildren(TreeFunction f) {
        //throw new UnsupportedOperationException("Not yet supported");
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        //throw new UnsupportedOperationException("Not yet supported");
    }
    @Override
    public void decompile(IndentPrintStream s) {
        s.print("this");
    }
    @Override
    public  void codeGenExpr(DecacCompiler compiler,int n){
        //
    }
}