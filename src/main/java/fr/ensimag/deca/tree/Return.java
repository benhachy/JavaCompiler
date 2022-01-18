package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
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
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class Return extends AbstractInst {
    private AbstractExpr expression;

    public Return(AbstractExpr expression) {
        this.expression=expression;
    }
    protected void verifyInst(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass, Type returnType) throws ContextualError{
            
            }
    protected void codeGenInst(DecacCompiler compiler){

    }
    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        throw new UnsupportedOperationException("Not yet supported");
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    // leaf node => nothing to do
    }
}