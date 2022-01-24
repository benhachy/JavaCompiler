package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tools.SymbolTable.Symbol;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Dictionary associating identifier's ExpDefinition to their names.
 * 
 * This is actually a linked list of dictionaries: each EnvironmentExp has a
 * pointer to a parentEnvironment, corresponding to superblock (eg superclass).
 * 
 * The dictionary at the head of this list thus corresponds to the "current"
 * block (eg class).
 * 
 * Searching a definition (through method get) is done in the "current"
 * dictionary and in the parentEnvironment if it fails.
 * 
 * Insertion (through method declare) is always done in the "current"
 * dictionary.
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class EnvironmentExp {
    // A FAIRE : implémenter la structure de donnée représentant un
    // environnement (association nom -> définition, avec possibilité
    // d'empilement).
    private HashMap<SymbolTable.Symbol, ExpDefinition> envExp;
    private HashMap<SymbolTable.Symbol, MethodDefinition> methods;
    EnvironmentExp parentEnvironment;

    public EnvironmentExp(EnvironmentExp parentEnvironment) {
        this.parentEnvironment = parentEnvironment;
        envExp = new HashMap<SymbolTable.Symbol, ExpDefinition>();
        methods = new HashMap<SymbolTable.Symbol, MethodDefinition>();
    }

    public static class DoubleDefException extends Exception {
        private static final long serialVersionUID = -2733379901827316441L;

        public DoubleDefException(String message) {
            super(message);
        }
    }

    public void empiler(EnvironmentExp env) {
        for (Symbol s : env.getEnvExp().keySet()) {
            if (!envExp.containsKey(s)) {

                envExp.put(s, env.getEnvExp().get(s));
            }
        }
    }

    public HashMap<SymbolTable.Symbol, MethodDefinition> getMethods() {
        return methods;
    }

    public HashMap<SymbolTable.Symbol, ExpDefinition> getEnvExp() {
        return envExp;
    }

    public int getOrdre(Symbol key) {
        return key.getOrdre();
    }

    /**
     * Return the definition of the symbol in the environment, or null if the
     * symbol is undefined.
     */
    public ExpDefinition get(Symbol key) {
        if (envExp.containsKey(key)) {
            return envExp.get(key);
        }
        return null;
    }

    /**
     * Add the definition def associated to the symbol name in the environment.
     * 
     * Adding a symbol which is already defined in the environment,
     * - throws DoubleDefException if the symbol is in the "current" dictionary
     * - or, hides the previous declaration otherwise.
     * 
     * @param name
     *             Name of the symbol to define
     * @param def
     *             Definition of the symbol
     * @throws DoubleDefException
     *                            if the symbol is already defined at the "current"
     *                            dictionary
     *
     */
    public void declare(Symbol name, ExpDefinition def) throws DoubleDefException {
        if (envExp.containsKey(name)) {
            throw new DoubleDefException("La variable " + name.getName() + " est déjà définie");
        }
        envExp.put(name, def);
        if (def.isMethod()) {
            methods.put(name, (MethodDefinition) def);
        }
    }

    public void update(Symbol name, ExpDefinition def) {
        if (envExp.containsKey(name)) {
            envExp.replace(name, def);
        } else {
            envExp.put(name, def);
        }
    }

    public int contains(Symbol s) {
        int b = -1;
        if (envExp.containsKey(s)) {
            b = envExp.get(s).getIndex();
        }
        if (b > -1) {
            return b;
        } else {
            if (parentEnvironment == null) {
                return -1;
            }
            b = parentEnvironment.contains(s);
        }
        return b;
    }

    public void afficher() {
        System.out.print(" {");
        for (Symbol s : envExp.keySet()) {
            System.out.println(" " + s);
        }
        System.out.print(" }");
    }

}