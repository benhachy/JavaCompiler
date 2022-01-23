package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class ListDeclField extends TreeList<AbstractDeclField> {
    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractDeclField e : getList()){
            e.decompile(s);
            s.println("");
        }
    }
    

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        for(AbstractDeclField f : this.getList())
        {
            f.prettyPrintChildren(s,prefix);
        }
    }
}