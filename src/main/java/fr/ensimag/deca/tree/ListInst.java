package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;

/**
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class ListInst extends TreeList<AbstractInst> {

    /**
     * Implements non-terminal "list_inst" of [SyntaxeContextuelle] in pass 3
     * @param compiler contains "env_types" attribute
     * @param localEnv corresponds to "env_exp" attribute
     * @param currentClass 
     *          corresponds to "class" attribute (null in the main bloc).
     * @param returnType
     *          corresponds to "return" attribute (void in the main bloc).
     */    
    public void verifyListInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
            //System.out.println("ListInst.java::VerifyListInst");
                if(getList().size()==0)
                {
                    return;
                    // throw new ContextualError("Il faut au maoins une instruction",getLocation());
                }
                AbstractInst lastInst = getList().get(0);
                for (AbstractInst i : getList()) {
                    i.verifyInst(compiler, localEnv, currentClass, returnType);
                    lastInst = i;
                }
                if(!returnType.isVoid() && !(lastInst instanceof Return))
                {
                    throw new ContextualError(" le retour doit etre de type "+ returnType.getName(),lastInst.getLocation() );
                }
            //<AbstractInst> listInstances = this.getList();
        // throw new UnsupportedOperationException("not yet implemented");
    }

    public void codeGenListInst(DecacCompiler compiler) {
        //System.out.println(":: ListInst :: codeGenListInst");
        for (AbstractInst i : getList()) {
            i.codeGenInst(compiler);
        }
    }

    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractInst i : getList()) {
            i.decompileInst(s);
            s.println();
        }
    }
}
