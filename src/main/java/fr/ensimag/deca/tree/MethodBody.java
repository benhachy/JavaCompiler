package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class MethodBody extends AbstractMethodBody {
     ListDeclVar declVariables;
     ListInst insts;
    public MethodBody(ListDeclVar declVariables,
            ListInst insts) {
        this.declVariables = declVariables;
        this.insts = insts;
    }
    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        throw new UnsupportedOperationException("Not yet supported");
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    public void verifyMethodBody(DecacCompiler compiler,EnvironmentExp localEnv, 
    EnvironmentExp parametres, ClassDefinition currentClass, Type expectedReturn) 
        throws ContextualError{
            parametres.empiler(localEnv);
            declVariables.verifyListDeclVariable(compiler,parametres,currentClass);
            if((insts.getList().size()==0) && !expectedReturn.isVoid())
            {
                throw new ContextualError("Le retour doit etre de type "+expectedReturn.getName(),getLocation());
            }
            insts.verifyListInst(compiler,parametres,currentClass,expectedReturn);

        }
}