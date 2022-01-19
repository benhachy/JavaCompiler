package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class ListDeclField extends TreeList<AbstractDeclField> {
    @Override
    public void decompile(IndentPrintStream s) {
    }
    

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        for(AbstractDeclField f : this.getList())
        {
            f.prettyPrintChildren(s,prefix);
        }
    }
}