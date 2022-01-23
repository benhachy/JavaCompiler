package fr.ensimag.deca.tree;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

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
                if(!expression.verifyCompatibility(localEnv,t,returnType))
                {
                    throw new ContextualError("le type du retour et le retour ne sont pas compatibles ",getLocation());
                }
                
            
            }
    @Override
    protected void codeGenInst(DecacCompiler compiler){
        //obtenir la variable pour le reour
        compiler.addInstruction(new LOAD(new ImmediateInteger(1),Register.getR(0)));
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