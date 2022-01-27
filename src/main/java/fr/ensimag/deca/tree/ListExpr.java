package fr.ensimag.deca.tree;
import java.util.List;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.tools.IndentPrintStream;

import java.io.PrintStream;
import java.util.Collections;

/**
 * List of expressions (eg list of parameters).
 *
 * @author gl03
 * @date 01/01/2022
 */
public class ListExpr extends TreeList<AbstractExpr> {


    public void verifySignature(DecacCompiler compiler,
    EnvironmentExp localEnv, ClassDefinition currentClass,Signature sig) throws ContextualError{
        int param = 0;
        
        for (AbstractExpr i : getList()) {
            i.verifyExpr(compiler, localEnv, currentClass);
            if(!i.verifyCompatibility(localEnv, sig.paramNumber(param),i.getType())){
                throw new ContextualError("le parametre "+(param+1)+" doit etre de type "+sig.paramNumber(param).getName(), i.getLocation());
            }
            ++param;
        }
    }

    @Override
    public void decompile(IndentPrintStream s) {
    
        for (AbstractExpr i : getList()) {

            i.decompile(s);
            s.print(", ");
        }
    }
    
}
