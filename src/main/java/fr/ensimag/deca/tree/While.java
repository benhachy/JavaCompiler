package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.Label;

import static org.mockito.ArgumentMatchers.nullable;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class While extends AbstractInst {
    private AbstractExpr condition;
    private ListInst body;
    public static int numWhile =0;

    public AbstractExpr getCondition() {
        return condition;
    }

    public ListInst getBody() {
        return body;
    }

    public While(AbstractExpr condition, ListInst body) {
        Validate.notNull(condition);
        Validate.notNull(body);
        this.condition = condition;
        this.body = body;
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        System.out.println("::While.java:: codeGenInst");
        Label beginWhile = new Label("beginWhile"+numWhile);
        Label endWhile = new Label("endWhile"+numWhile);
        Label instWhile = new Label("instWhile"+numWhile);
        numWhile++;
        compiler.addLabel(beginWhile);
        condition.codeGenOpBool(compiler, null, null,true , instWhile, endWhile, 2);
        compiler.addInstruction(new BRA(endWhile));
        compiler.addLabel(instWhile);
        body.codeGenListInst(compiler);
        compiler.addInstruction(new BRA(beginWhile));
        compiler.addLabel(endWhile);
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        //System.out.println("::While.java::verifyInst ");
        condition.verifyCondition(compiler, localEnv, currentClass);
        body.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("while (");
        getCondition().decompile(s);
        s.println(") {");
        s.indent();
        getBody().decompile(s);
        s.unindent();
        s.print("}");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        condition.iter(f);
        body.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        body.prettyPrint(s, prefix, true);
    }

}
