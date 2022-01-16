package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.FloatType;
import fr.ensimag.deca.context.IntType;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.WFLOAT;
import fr.ensimag.ima.pseudocode.instructions.WFLOATX;
import fr.ensimag.ima.pseudocode.instructions.WINT;
import fr.ensimag.ima.pseudocode.instructions.WSTR;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Print statement (print, println, ...).
 *
 * @author gl03
 * @date 01/01/2022
 */
public abstract class AbstractPrint extends AbstractInst {

    private boolean printHex;
    private ListExpr arguments = new ListExpr();
    
    abstract String getSuffix();

    public AbstractPrint(boolean printHex, ListExpr arguments) {
        Validate.notNull(arguments);
        this.arguments = arguments;
        this.printHex = printHex;
    }

    public ListExpr getArguments() {
        return arguments;
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        if(arguments.size() == 0)
        {
            return;
        }
        // if(arguments.size() != 1)
        // {
        //     throw new ContextualError("Print prend un argument", this.getLocation());
        // }
        //System.out.println(":: AbstractPrint :: verifyInst");
        for (AbstractExpr argument : getArguments().getList())
        {
            Type chaine = argument.verifyExpr(compiler, localEnv, currentClass);
            
            if(!chaine.isFloat() && !chaine.isInt() && !chaine.isString())
            {
                throw new ContextualError("Print ne prend en argument que : entier,réel ou chaine de caractéres ", this.getLocation());
            }
        }
        
    }
    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        //System.out.println(":: AbstractPrint :: codeGenInst");
        for (AbstractExpr a : getArguments().getList()) {
            a.codeGenPrint(compiler);
            if(a.getType().isInt())
            {
                compiler.addInstruction(new WINT());
            }
            else if(a.getType().isFloat())
            {
                if(getPrintHex())
                {
                    compiler.addInstruction(new WFLOATX());
                }
                else{
                    compiler.addInstruction(new WFLOAT());
                }
            }
            else{
                System.out.println("i skiped this one "); 
            }
        }
    }

    private boolean getPrintHex() {
        return printHex;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("print"+getSuffix()+"(");
        arguments.decompile(s);
        s.print(");");
        //throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        arguments.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        arguments.prettyPrint(s, prefix, true);
    }

}
