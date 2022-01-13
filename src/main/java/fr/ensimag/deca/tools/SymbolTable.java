package fr.ensimag.deca.tools;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrTokenizer;

/**
 * Manage unique symbols.
 * 
 * A Symbol contains the same information as a String, but the SymbolTable
 * ensures the uniqueness of a Symbol for a given String value. Therefore,
 * Symbol comparison can be done by comparing references, and the hashCode()
 * method of Symbols can be used to define efficient HashMap (no string
 * comparison or hashing required).
 * 
 * @author gl03
 * @date 01/01/2022
 */
public class SymbolTable {
    private Map<String, Symbol> map;
    
    public SymbolTable()
    {
        map = new HashMap<String, Symbol>();
    }
        
    /**
     * Create or reuse a symbol.
     * 
     * If a symbol already exists with the same name in this table, then return
     * this Symbol. Otherwise, create a new Symbol and add it to the table.
     */
    

    
    public Symbol create(String name) {
    
        if (map.containsKey(name))
        {
            return map.get(name);
        }
        try{
        SymbolTable.Symbol newSymbol = new Symbol(name);
        map.put(name,newSymbol);
        }
        catch  (UnsupportedOperationException e) {
            System.out.println("Symbol creation failed");
        }
        return map.get(name);
       
    }
    public static Symbol creerSymbol(String name) {
    
        return new Symbol(name);
       
    }

    public static class Symbol { 
        // Constructor is private, so that Symbol instances can only be created
        // through SymbolTable.create factory (which thus ensures uniqueness
        // of symbols).
        private String name;
        private int ordre;
        private Symbol(String name) {
            super();
            this.name = name; 
        }

        public String getName() {
            return name;
        }
        public void setOrdre(int val)
        {
            this.ordre = val;
        }
        public int getOrdre()
        {
            return this.ordre;
        }

        @Override
        public String toString() {
            return name;
        }

        

        @Override
        public boolean equals(Object o) 
        {
            if(o instanceof Symbol) {
                Symbol symbol = (Symbol)o;
                if(name.equals(symbol.name)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            int hashCode = 0;
            for(int i = 0; i < name.length(); i++) 
            {
                hashCode += (int)name.charAt(i);
            }
            return hashCode;
        }

    }
}
