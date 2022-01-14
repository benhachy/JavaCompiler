package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;

/**
 *
 * @author gl03
 * @date 01/01/2022
 */
public class And extends AbstractOpBool {

    public And(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "&&";
    }
    // @Override
    // protected  void codeGenCondition(DecacCompiler compiler,boolean bool,Label etiquette){
    //     AbstractExpr rOp = getRightOperand();
    //     AbstractExpr lOp = getLeftOperand();
    //     if ( bool){
    //         lOp.codeGenCondition(compiler,!bool,etiquette);
    //         rOp.codeGenCondition(compiler,bool,etiquette);
    //     }
    //     else{
    //         lOp.codeGenCondition(compiler,bool,etiquette);
    //         rOp.codeGenCondition(compiler,bool,etiquette);
    //     }
        
    // }

}
