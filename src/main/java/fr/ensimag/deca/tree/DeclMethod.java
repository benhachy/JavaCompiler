package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.LabelOperand;

import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclMethod extends AbstractDeclMethod {
    AbstractIdentifier type;
    AbstractIdentifier name;
    ListDeclParam   paramDecl;
    AbstractMethodBody  methodBody;
    public DeclMethod(AbstractIdentifier type,AbstractIdentifier name,ListDeclParam   paramDecl,AbstractMethodBody methodBody){
        this.type= type;
        this.name=name;
        this.paramDecl= paramDecl;
        this.methodBody=methodBody;
    }
    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        name.prettyPrint(s, prefix, false);
        paramDecl.prettyPrint(s, prefix, false);
        methodBody.prettyPrint(s, prefix, false);
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        name.iter(f);
        paramDecl.iter(f);
        methodBody.iter(f);
    }

    public void creerEtStockerLabel(DecacCompiler compiler,DeclClass declClass){
        //creer l'etiquete du methode
        Label label = new Label("code."+declClass.getIdentifier().getName().getName()+"."+name.getName());
        //insertion des etiquetes des methodes sur la table des methodes
        compiler.addInstruction(new LOAD(new LabelOperand(label),Register.getR(0)));
        compiler.addInstruction(new STORE(Register.getR(0), new RegisterOffset(Register.getPosGB(),Register.GB)));
        Register.updatePosGB();
    }

    //generer le code ass pour le methode
    public void genCodeMethode(DecacCompiler compiler,DeclClass declClass){
        
        //creer l'etiquete du methode
        Label label = new  Label("code."+declClass.getClass()+name.getName());
        //inserer l'etiquete du methode
        compiler.addLabel(label);

        //code pour la declaration des parametres 

        //paramDecl
        //code pour les instructions de methode
        //methodBody
    }

    @Override
    public void verifyMethod(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError{
        type.setDefinition(compiler.getDefinition(type.getName()));
        System.out.println(" type "+type.getName()+" name "+name.getName());
        Type expectedReturn = compiler.get(type.getName()).getType();
        Signature signature = new Signature();
        EnvironmentExp paramsEnv = paramDecl.verifyListParam(compiler,signature);
        MethodDefinition method = new MethodDefinition(expectedReturn,getLocation(),signature,0);
        name.setDefinition(method);
        try{
            localEnv.declare(name.getName(),method);
        }
        catch (EnvironmentExp.DoubleDefException e)
        {
            
            throw new ContextualError("la méthode "+ name.getName()+" est déjà définie", getLocation());
        }
        
        methodBody.verifyMethodBody(compiler,localEnv,paramsEnv,currentClass,expectedReturn);
    }

}
