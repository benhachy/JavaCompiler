package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Operand;

import org.antlr.v4.runtime.atn.SemanticContext.Operator;
import org.apache.log4j.Logger;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class ListDeclClass extends TreeList<AbstractDeclClass> {
    private static final Logger LOG = Logger.getLogger(ListDeclClass.class);
    
    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractDeclClass c : getList()) {
            c.decompile(s);
            s.println();
        }
    }

    /**
     * Pass 1 of [SyntaxeContextuelle]
     */
    void verifyListClass(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify listClass: start");
        System.out.println("ListDeclClass.java :: verifyListClass");
        for (AbstractDeclClass c : getList()) {
            c.verifyClass(compiler);
        }
        // LOG.debug("verify listClass: end");
    }

    /**
     * Pass 2 of [SyntaxeContextuelle]
     */
    public void verifyListClassMembers(DecacCompiler compiler) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }
    
    /**
     * Pass 3 of [SyntaxeContextuelle]
     */
    public void verifyListClassBody(DecacCompiler compiler) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public void creerTableMethodes(DecacCompiler compiler){
        for (AbstractDeclClass declClass : getList()) {
            declClass.insertionClassTableMethodes(compiler);
        }
    }
    public void genCodeInitializationEtMethodes(DecacCompiler compiler){
        //add table entry for object and equals
        compiler.addInstruction(new LOAD(null,Register.getR(0)));
        compiler.addInstruction(new STORE(Register.getR(0),new RegisterOffset(1,Register.GB)));
        Operand equals = new Label("code.Object.equals");
        //compiler.addInstruction(new LOAD(equals,Register.getR(0));
        //compiler.addInstruction(new STORE(Register.getR(0),new RegisterOffset(2,Register.GB)));
        for (AbstractDeclClass declClass : getList()) {
            declClass.genCodeInitializationChampsEtMethodes(compiler);
        }
    }


}
