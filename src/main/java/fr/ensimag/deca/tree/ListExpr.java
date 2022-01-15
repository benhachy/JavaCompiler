package fr.ensimag.deca.tree;
import java.util.List;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.util.Collections;

/**
 * List of expressions (eg list of parameters).
 *
 * @author gl03
 * @date 01/01/2022
 */
public class ListExpr extends TreeList<AbstractExpr> {

    // @Override
    // protected abstract void codeGenInst(DecacCompiler compiler){

    // }
    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractExpr i : getList()) {
            i.decompile(s);
        }
    }
}
