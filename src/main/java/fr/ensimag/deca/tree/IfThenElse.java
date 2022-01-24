package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.Label;

/**
 * Full if/else if/else statement.
 *
 * @author gl03
 * @date 01/01/2022
 */
public class IfThenElse extends AbstractInst {
    
    private final AbstractExpr condition; 
    private final ListInst thenBranch;
    private ListInst elseBranch;
    public static int numIf =0;
    public IfThenElse(AbstractExpr condition, ListInst thenBranch, ListInst elseBranch) {
        Validate.notNull(condition);
        Validate.notNull(thenBranch);
        Validate.notNull(elseBranch);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
    public void setElseBranch(ListInst elseBranch){
        this.elseBranch=elseBranch;
    }
    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        condition.verifyCondition(compiler, localEnv, currentClass);
        thenBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
        elseBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        Label beginIf = new Label("beginIf"+numIf);
        Label finElse = new Label("finElse"+numIf);
        Label ifInst = new Label("ifInst"+numIf);
        Label elseInst = new Label("elseInst"+numIf);
        numIf++;
        compiler.addLabel(beginIf);
        condition.codeGenOpBool(compiler, null, null,true , ifInst, finElse, 2);
        compiler.addInstruction(new BRA(elseInst));
        compiler.addLabel(ifInst);
        thenBranch.codeGenListInst(compiler);
        compiler.addInstruction(new BRA(finElse));
        compiler.addLabel(elseInst);
        elseBranch.codeGenListInst(compiler);
        //compiler.addInstruction(new BRA(beginIf));
        compiler.addLabel(finElse);;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("if (");
        condition.decompile(s);
        s.print(") {\n");
        this.thenBranch.decompile(s);
        s.print("}\n");
        s.print("else{\n");
        this.elseBranch.decompile(s);
        s.print("}\n");

    }

    @Override
    protected
    void iterChildren(TreeFunction f) {
        condition.iter(f);
        thenBranch.iter(f);
        elseBranch.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        thenBranch.prettyPrint(s, prefix, false);
        elseBranch.prettyPrint(s, prefix, true);
    }
}
