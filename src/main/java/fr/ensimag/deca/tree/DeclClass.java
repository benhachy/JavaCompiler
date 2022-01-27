package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.NullOperand;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.BSR;
import fr.ensimag.ima.pseudocode.instructions.LEA;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.RTS;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.instructions.SUBSP;
import fr.ensimag.ima.pseudocode.instructions.TSTO;
import java.util.HashMap;
import fr.ensimag.deca.tools.SymbolTable;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import fr.ensimag.ima.pseudocode.LabelOperand;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class DeclClass extends AbstractDeclClass {
    AbstractIdentifier identifier;
    AbstractIdentifier classExtension;
    ListDeclField feildDecl;
    ListDeclMethod methodDecl;
    private TreeMap<Label,Integer> listEtiquetteMethod = new TreeMap<Label,Integer>();
    private TreeMap<Integer, Label> newHashMap = new TreeMap<Integer, Label>();
    private List<String> listFields = new ArrayList<String>();
    private HashMap<SymbolTable.Symbol, Integer> hashMapMethodIndex = new HashMap<SymbolTable.Symbol, Integer>();
    private HashMap<SymbolTable.Symbol, Integer> hashMapFieldIndex = new HashMap<SymbolTable.Symbol, Integer>();
    private int index = 0;
    private int indexFields = 0;

    public DeclClass(AbstractIdentifier identifier, AbstractIdentifier classExtension, ListDeclField feildDecl,
            ListDeclMethod methodDecl) {
        this.identifier = identifier;
        this.classExtension = classExtension;
        this.feildDecl = feildDecl;
        this.methodDecl = methodDecl;
    }

    // public int getIndexMethod(Label l) {
    //     return listEtiquetteMethod.indexOf(l);
    // }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class ");
        identifier.decompile(s);
        if (!classExtension.getName().getName().equals("Object")) {
            s.print(" extends ");
            classExtension.decompile(s);
        }
        s.println(" {");
        feildDecl.decompile(s);
        s.println("");
        methodDecl.decompile(s);
        s.println("}");
    }

    public AbstractIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    protected void verifyClass(DecacCompiler compiler) throws ContextualError {

        ClassDefinition superClass = compiler.getClass(classExtension.getName());
        ClassType c = new ClassType(identifier.getName(), getLocation(), superClass);
        if (superClass == null) {
            throw new ContextualError("la super classe " + classExtension.getName() + " n'est pas définie",
                    getLocation());
        }

        try {
            classExtension.setDefinition(superClass);
            identifier.setDefinition((new ClassDefinition(c, getLocation(), classExtension.getClassDefinition())));
            compiler.declare(identifier.getName(), identifier.getClassDefinition());
        } catch (DoubleDefException e) {
            throw new ContextualError("la classe " + identifier.getName() + " est déjà définie", getLocation());
        } catch (DecacInternalError e) {
            throw new ContextualError(classExtension.getName() + " n'est pas une class", getLocation());
        }
    }

    @Override
    protected void verifyClassMembers(DecacCompiler compiler)
            throws ContextualError {
        EnvironmentExp envExpF = new EnvironmentExp(compiler.getEnv(classExtension.getName()));
        int indiceField = classExtension.getClassDefinition().getNumberOfFields();
        int indiceMethod = classExtension.getClassDefinition().getNumberOfMethods() + 1;
        for (AbstractDeclField f : feildDecl.getList()) {
            f.verifyFeild(compiler, envExpF, classExtension.getClassDefinition(), identifier.getClassDefinition(),
                    indiceField);
            ++indiceField;
        }
        for (AbstractDeclMethod f : methodDecl.getList()) {
            indiceMethod += f.verifyMethod(compiler, envExpF, identifier.getClassDefinition(), indiceMethod);
        }
        ClassDefinition newDef = identifier.getClassDefinition();
        newDef.setNumberOfFields(feildDecl.size() + classExtension.getClassDefinition().getNumberOfFields());
        newDef.setNumberOfMethods(indiceMethod - 1);

        identifier.setDefinition(newDef);
        compiler.update(identifier.getName(), newDef);
        compiler.setEvn(identifier.getName(), envExpF);
    }

    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
        EnvironmentExp envExpR = new EnvironmentExp(null);
        for (AbstractDeclMethod f : methodDecl.getList()) {
            f.verifyBody(compiler, envExpR, identifier.getClassDefinition());
        }
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        identifier.prettyPrint(s, prefix, false);
        classExtension.prettyPrint(s, prefix, false);
        feildDecl.prettyPrint(s, prefix, false);
        methodDecl.prettyPrint(s, prefix, true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        identifier.iter(f);
        classExtension.iter(f);
        feildDecl.iter(f);
        methodDecl.iter(f);
    }

    public void createLabelList(DecacCompiler compiler, Symbol currentClass) {

        if (currentClass.equals(SymbolTable.creerSymbol("0"))) {
            return;
        }

        ClassDefinition definitionClass = compiler.getClass(currentClass);

        createLabelList(compiler, definitionClass.getSuperClass().getType().getName());
        EnvironmentExp envClass = compiler.getEnv(currentClass);
        if (envClass == null) {

        }
        HashMap<SymbolTable.Symbol, ExpDefinition> hashMapEnv = envClass.getEnvExp();
        for (Symbol s : hashMapEnv.keySet()) {
            

            if (envClass.get(s).isMethod()) {

                if (hashMapMethodIndex.containsKey(s)) {

                    int newIndex = hashMapMethodIndex.get(s);
                    newHashMap.remove(envClass.get(s).getIndex());
                    newHashMap.put(envClass.get(s).getIndex(), new Label("code." + currentClass + "." + s.getName()));
                } else {
                    newHashMap.put(envClass.get(s).getIndex(), new Label("code." + currentClass + "." + s.getName()));
                    hashMapMethodIndex.put(s, envClass.get(s).getIndex());
                    index++;
                }

            }

        }

    }

    public void createListFields(DecacCompiler compiler, Symbol currentClass) {
        if (currentClass.equals(SymbolTable.creerSymbol("0"))) {
            return;
        }

        ClassDefinition definitionClass = compiler.getClass(currentClass);

        createListFields(compiler, definitionClass.getSuperClass().getType().getName());
        EnvironmentExp envClass = compiler.getEnv(currentClass);
        if (envClass == null) {

        }
        HashMap<SymbolTable.Symbol, ExpDefinition> hashMapEnv = envClass.getEnvExp();
        for (Symbol s : hashMapEnv.keySet()) {

            // edit this so we can check if its a method or not
            if (envClass.get(s).isField()) {
                listFields.add(indexFields, currentClass + "." + s.getName());
                hashMapFieldIndex.put(s, indexFields);
                indexFields++;

            }

        }

    }


    public void afficherFields() {
        int i = 0;
        for (String l : listFields) {
            System.out.println(i + " " + l);
            i++;
        }
    }

    public void insertionClassTableMethodes(DecacCompiler compiler) {
        // on verifie si la class extend object

        compiler.addComment("Code de la table des méthodes de " + identifier.getName());
        if (classExtension.getName().getName().equals("Object")) {
            compiler.addInstruction(new LEA(new RegisterOffset(1, Register.GB), Register.getR(0)));
        } else {
            compiler.addInstruction(new LEA(Identifier.getVariableAddress(classExtension.getName()), Register.getR(0)));
        }
        compiler.addInstruction(new STORE(Register.getR(0), new RegisterOffset(Register.positionGB, Register.GB)));
        Identifier.addVariableAddress(identifier.getName(), Register.positionGB, Register.GB);
        Register.updatePosGB();
        EnvironmentExp envClass = compiler.getEnv(identifier.getClassDefinition().getSuperClass().getType().getName());

        // for (AbstractDeclField f : feildDecl.getList()) {
        //     System.out.println(identifier.getName() + "." + f.getName().getName() + " "
        //             + f.getName().getFieldDefinition().getIndex());
        // }
        createLabelList(compiler, identifier.getName());
       // System.out.println("definition de la class " + identifier.getName());
        createListFields(compiler, identifier.getName());
        //afficherFields();
        Set<Integer> keys = newHashMap.keySet();
      for(Integer key: keys){
            compiler.addInstruction(new LOAD(new LabelOperand(newHashMap.get(key) ), Register.getR(0)));
            compiler.addInstruction(new STORE(Register.getR(0), new RegisterOffset(Register.getPosGB(), Register.GB)));
            Register.updatePosGB();
        }
    }

    public void genCodeInitializationChampsEtMethodes(DecacCompiler compiler) {

        compiler.addComment("Initialisation des champs de " + identifier.getName());
        Label label = new Label("init." + identifier.getName());
        compiler.addLabel(label);

        int nmChamps = feildDecl.getList().size();
        compiler.addInstruction(new TSTO(new ImmediateInteger(nmChamps + 1)));
        compiler.addInstruction(new BOV(new Label("pile_pleine")));
        compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.getR(1)));
        for (AbstractDeclField champ : feildDecl.getList()) {
            champ.codeGenFeild(compiler);
        }
        if (!classExtension.getName().getName().equals("Object")) {
            compiler.addComment(
                    "Appel de l'initialisation des champs hérités de " + classExtension.getName().getName());
            compiler.addInstruction(new PUSH(Register.getR(1)));
            Label labelInitSuper = new Label("init." + classExtension.getName().getName());
            compiler.addInstruction(new BSR(labelInitSuper));
            compiler.addInstruction(new SUBSP(new ImmediateInteger(1)));
        }
        compiler.addInstruction(new RTS());
        for (AbstractDeclMethod methode : methodDecl.getList()) {
            methode.genCodeMethode(compiler, this);
        }
    }

}
