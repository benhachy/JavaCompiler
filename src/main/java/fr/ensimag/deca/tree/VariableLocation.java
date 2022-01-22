package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;

public class VariableLocation {
    int pos; //la position relavie a un registre de la variable
    Register register; // pour un attribut d'un objet sera Register.get(1)

    public VariableLocation(int pos,Register register){
        this.pos = pos;
        this.register = register;
    }

    public DAddr getVariableAddress(){
        return new RegisterOffset(pos, register);
    }

    @Override
    public String toString() {
        String s = "pos: "+pos+" register ";
        if(register.equals(Register.GB)){
            s+="GB";
        }else if(register.equals(Register.LB)){
            s+="LB";
        }else if(register.equals(Register.getR(1))){
            s+="R1";
        }
        return s;
    }
    
}

