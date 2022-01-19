package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclMethod extends AbstractDeclMethod {
    AbstractIdentifier identifier;
    AbstractIdentifier classExtension;
    ListDeclParam   paramDecl;
    MethodBody  methodBody;
    public DeclMethod(AbstractIdentifier identifier,AbstractIdentifier classExtension,ListDeclParam   paramDecl,MethodBody  methodBody){
        this.identifier= identifier;
        this.classExtension=classExtension;
        this.paramDecl= paramDecl;
        this.methodBody=methodBody;
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

    public void creerEtStockerLabel(DecacCompiler compiler){
        //creer l'etiquete du methode
        Label label = new Label("code."+classExtension.getName()+"."+identifier.getName());
        //insertion des etiquetes des methodes sur la table des methodes
        compiler.addInstruction(new LOAD(label,Register.getR(0));
        compiler.addInstruction(new STORE(Register.getR(0), new RegisterOffset(4,Register.GB)));
    }

    //generer le code ass pour le methode
    public void genCodeMethode(DecacCompiler compiler){
        
        //creer l'etiquete du methode
        Label label = new  Label("code."+classExtension.getName()+identifier.getName());
        //inserer l'etiquete du methode
        compiler.addLabel(label);

        //code pour la declaration des parametres 

        //paramDecl
        //code pour les instructions de methode
        //methodBody
    }

}
