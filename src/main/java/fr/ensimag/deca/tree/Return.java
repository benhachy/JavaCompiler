package fr.ensimag.deca.tree;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.instructions.BRA;

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
    @Override
    protected void verifyInst(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass, Type returnType) throws ContextualError{
                Type t = expression.verifyExpr(compiler,localEnv,currentClass);
                if(!expression.verifyCompatibility(localEnv,returnType,t))
                {
                    throw new ContextualError("le type du retour et le retour ne sont pas compatibles ",getLocation());
                }
                
            
            }
    @Override
    protected void codeGenInst(DecacCompiler compiler){
        expression.codeGenExpr(compiler, 0);
        compiler.addInstruction(new BRA(compiler.getReturnLabel()));
    }
    @Override
    public void decompile(IndentPrintStream s) {
        s.print("return ");
        expression.decompile(s);
        s.print(" ;");

    }
    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iter(f);
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrint(s,prefix,false);
    }
}