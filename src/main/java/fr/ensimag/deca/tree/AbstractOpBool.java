package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    public static int numFin =0;
    private String labelEnd = "E.Fin";
    private String labelStart = "E.Start";
    Label operand = new Label("E");
    Label endOperand = new Label("E.end");

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
            System.out.println("::AbstractOpCm.java:: verifyExpr");
            AbstractExpr rOp = getRightOperand();
            AbstractExpr lOp = getLeftOperand();
            Type type1 = rOp.verifyExpr(compiler, localEnv, currentClass);
            Type type2 = lOp.verifyExpr(compiler, localEnv, currentClass);
            if(!(type1.isBoolean() && type2.isBoolean()))
            {
                throw new ContextualError("Les opérations booleennes ne supportent que des booleens", getLocation());
            }
            setType(new BooleanType(SymbolTable.creerSymbol(getOperatorName())));
            return getType();
    }
    @Override
    public void codeGenExpr(DecacCompiler compiler,int n){
        System.out.println("::AbstractOpBool.java:: codeGenExpr");
        // AbstractExpr rOp = getRightOperand();
        // AbstractExpr lOp = getLeftOperand();
        // lOp.codeGenCode(compiler, C, b, E);
        // compiler.addInstruction(new LOAD(C, Register.getR(0)));
        // rOp.codeGenCode(compiler, C, b, E);
        // this.codeGenOpBool(compiler,b, 0);
        // compiler.addInstruction(new LOAD(C, Register.getR(0)));
        // compiler.addInstruction(new CMP(new ImmediateInteger(0), Register.getR(0)));
        // if(b)
        // {
        //     compiler.addInstruction(new BNE(E));
        // }
        // else
        // {
        //     compiler.addInstruction(new BEQ(E));
        // } 
        AbstractExpr leftOperand = getLeftOperand();
        AbstractExpr rightOperand = getRightOperand();
        Label Operand = new Label(labelStart+numFin);
        Label endOperand = new Label(labelEnd+numFin);
        ++numFin;
        compiler.addLabel(Operand);
        //leftOperand.codeGenOpBool(compiler,Register.getR(0), Register.getR(0),false,Operand, n);
        leftOperand.codeGenExpr(compiler,n);//true
        compiler.addInstruction(new PUSH(Register.getR(n)));
        //compiler.addLabel(endOperand);
        rightOperand.codeGenOpBool(compiler,Register.getR(0), Register.getR(0),true,Operand,endOperand, n);//false
        compiler.addInstruction(new LOAD(Register.getR(n) ,Register.getR(0)));
        compiler.addInstruction(new POP(Register.getR(n)));
        this.codeGenOpBool(compiler,Register.getR(n) ,Register.getR(0), true, endOperand,endOperand, n);
        compiler.addLabel(endOperand);
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {

        codeGenOpBool(compiler, null, null, true, operand,endOperand, 2);
        compiler.addLabel(operand.addFin(55));
    }

}
