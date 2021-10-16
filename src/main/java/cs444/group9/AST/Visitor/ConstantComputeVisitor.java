/*******************************************************************************
 * ConstantComputeVisitor.java
 * 
 * A module implementing ConstantCompute Visitor.
 * ****************************************************************************/
package cs444.group9.AST.Visitor;

import cs444.group9.AST.Node.Body.CompilationUnitNode;
import cs444.group9.AST.Node.Declaration.ImportDeclNode;
import cs444.group9.AST.Node.Expression.*;
import cs444.group9.AST.Node.LiteralNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConstantComputeVisitor extends DefaultVisitor{

    private static final Logger logger = LogManager.getLogger(ConstantComputeVisitor.class);

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(CompilationUnitNode node) throws ASTException{
        super.visit(node);
    } // visit(CompilationUnitNode)

    @Override
    public void visit(UnaryExprNode unaryExprNode) throws iVisitor.ASTException{
        super.visit(unaryExprNode);
        if(unaryExprNode.UnaryExpression() != null){
            if(unaryExprNode.UnaryExpression().isConstant()){
                String op=unaryExprNode.UnaryOperator();
                LiteralNode exprConstVal=unaryExprNode.UnaryExpression().getConstant();
                unaryExprNode.setIsConstant(true);
                if(op != null){
                    if(op.equals(UnaryExprNode.MINUS_OPER)){
                        LiteralNode newConstVal;
                        if(exprConstVal.LiteralType().equals(LiteralNode.Literal_INT)){
                            newConstVal=new LiteralNode();
                            if(exprConstVal.IntegerLiteral().charAt(0) == '-'){
                                newConstVal.IntegerLiteral(exprConstVal.IntegerLiteral().substring(1));
                            } else {
                                newConstVal.IntegerLiteral("-" + exprConstVal.IntegerLiteral());
                            } // else
                        } else {
                            newConstVal=exprConstVal;
                        } // else
                        unaryExprNode.setConstantValue(newConstVal);
                    } else if (op.equals(UnaryExprNode.NEG_OPER)
                            && (exprConstVal.LiteralType().equals(LiteralNode.Literal_BOOL))){
                        LiteralNode newConstVal=new LiteralNode();
                        newConstVal.BooleanLiteral(!exprConstVal.BooleanLiteral());
                        unaryExprNode.setConstantValue(newConstVal);
                    } else {
                        //SHOULD NOT GET HERE
                        logger.error("Invalid use of constant with operator");
                        throw new ConstantComputeException();
                    } // else
                } else {
                    unaryExprNode.setConstantValue(unaryExprNode.UnaryExpression().getConstant());
                } // else
            } else if (unaryExprNode.PostfixExpression() != null && unaryExprNode.PostfixExpression().isConstant()){
                unaryExprNode.setConstantValue(unaryExprNode.PostfixExpression().getConstant());
            } // else if
        } // if
        if(unaryExprNode.PostfixExpression() != null){
            if(unaryExprNode.PostfixExpression().isConstant()){
                unaryExprNode.setIsConstant(true);
                unaryExprNode.setConstantValue(unaryExprNode.PostfixExpression().getConstant());
            } // if
        } // if
        if(unaryExprNode.CastExpression() != null){
            if(unaryExprNode.CastExpression().isConstant()){
                unaryExprNode.setIsConstant(true);
                unaryExprNode.setConstantValue(unaryExprNode.CastExpression().getConstant());
            } // if
        } // if
    } // visit(UnaryExprNode)

    @Override
    public void visit(BinOpExprNode binOpExprNode) throws iVisitor.ASTException{
        super.visit(binOpExprNode);
        if(binOpExprNode.UnaryExpr() != null) {
            if(binOpExprNode.UnaryExpr().isConstant()){
                binOpExprNode.setIsConstant(true);
                binOpExprNode.setConstantValue(binOpExprNode.UnaryExpr().getConstant());
            } // if
        } else if (binOpExprNode.RightExpr() != null) {
            if((binOpExprNode.LeftExpr().isConstant()) && (binOpExprNode.RightExpr().isConstant())){
                LiteralNode leftConst=binOpExprNode.LeftExpr().getConstant();
                LiteralNode rightConst=binOpExprNode.RightExpr().getConstant();
                LiteralNode calculated=new LiteralNode();
                String op=binOpExprNode.Operator();
                if(op.equals(BinOpExprNode.OPER_EQ)){
                    binOpExprNode.setIsConstant(true);
                    if(leftConst.isSameValue(rightConst)){
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_NEQ)){
                    binOpExprNode.setIsConstant(true);
                    if(leftConst.isSameValue(rightConst)){
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_OROR) || op.equals(BinOpExprNode.OPER_OR) ){
                    binOpExprNode.setIsConstant(true);
                    if(leftConst.BooleanLiteral() || rightConst.BooleanLiteral()){
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_AMPAMP) || op.equals(BinOpExprNode.OPER_AND) ){
                    binOpExprNode.setIsConstant(true);
                    if(leftConst.BooleanLiteral() && rightConst.BooleanLiteral()){
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_MULT)) {
                    binOpExprNode.setIsConstant(true);
                    Float product=Float.parseFloat(leftConst.IntegerLiteral()) * Float.parseFloat(rightConst.IntegerLiteral());
                    calculated.IntegerLiteral(product.toString());
                    binOpExprNode.setConstantValue(calculated);
                } else if (op.equals(BinOpExprNode.OPER_DIV)){
                    binOpExprNode.setIsConstant(true);
                    Float dividend=Float.parseFloat(leftConst.IntegerLiteral()) / Float.parseFloat(rightConst.IntegerLiteral());
                    calculated.IntegerLiteral(dividend.toString());
                    binOpExprNode.setConstantValue(calculated);
                } else if (op.equals(BinOpExprNode.OPER_PCT)){
                    binOpExprNode.setIsConstant(true);
                    Float mod=Float.parseFloat(leftConst.IntegerLiteral()) % Float.parseFloat(rightConst.IntegerLiteral());
                    calculated.IntegerLiteral(mod.toString());
                    binOpExprNode.setConstantValue(calculated);
                } else if (op.equals(BinOpExprNode.OPER_GE)){
                    binOpExprNode.setIsConstant(true);
                    Float leftFloat=Float.parseFloat(leftConst.IntegerLiteral());
                    Float rightFloat=Float.parseFloat(rightConst.IntegerLiteral());
                    if(leftFloat >= rightFloat){
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_GT)){
                    binOpExprNode.setIsConstant(true);
                    Float leftFloat=Float.parseFloat(leftConst.IntegerLiteral());
                    Float rightFloat=Float.parseFloat(rightConst.IntegerLiteral());
                    if(leftFloat > rightFloat){
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_LE)){
                    binOpExprNode.setIsConstant(true);
                    Float leftFloat=Float.parseFloat(leftConst.IntegerLiteral());
                    Float rightFloat=Float.parseFloat(rightConst.IntegerLiteral());
                    if(leftFloat <= rightFloat){
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_LT)){
                    binOpExprNode.setIsConstant(true);
                    Float leftFloat=Float.parseFloat(leftConst.IntegerLiteral());
                    Float rightFloat=Float.parseFloat(rightConst.IntegerLiteral());
                    if(leftFloat < rightFloat){
                        calculated.BooleanLiteral(true);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        calculated.BooleanLiteral(false);
                        binOpExprNode.setConstantValue(calculated);
                    } // else
                } else if (op.equals(BinOpExprNode.OPER_MINUS)){
                    binOpExprNode.setIsConstant(true);
                    Float difference=Float.parseFloat(leftConst.IntegerLiteral()) - Float.parseFloat(rightConst.IntegerLiteral());
                    calculated.IntegerLiteral(difference.toString());
                    binOpExprNode.setConstantValue(calculated);
                } else if (op.equals(BinOpExprNode.OPER_PLUS)){
                    binOpExprNode.setIsConstant(true);
                    if(leftConst.LiteralType().equals(LiteralNode.Literal_INT)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_INT)){
                        Float sum=Float.parseFloat(leftConst.IntegerLiteral()) + Float.parseFloat(rightConst.IntegerLiteral());
                        calculated.IntegerLiteral(sum.toString());
                        binOpExprNode.setConstantValue(calculated);
                    } else if (leftConst.LiteralType().equals(LiteralNode.Literal_STR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_STR)){
                        calculated.Stringliteral(leftConst.Stringliteral()
                                + rightConst.Stringliteral());
                        binOpExprNode.setConstantValue(calculated);
                    } else if (leftConst.LiteralType().equals(LiteralNode.Literal_INT)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_STR)){
                        calculated.Stringliteral(leftConst.IntegerLiteral()
                                + rightConst.Stringliteral());
                        binOpExprNode.setConstantValue(calculated);
                    } else if (leftConst.LiteralType().equals(LiteralNode.Literal_STR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_INT)){
                        calculated.Stringliteral(leftConst.Stringliteral()
                                + rightConst.IntegerLiteral());
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_STR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_CHAR))){
                        calculated.Stringliteral(leftConst.Stringliteral() + rightConst.Charliteral());
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_CHAR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_STR))){
                        calculated.Stringliteral(leftConst.Charliteral() + rightConst.Stringliteral());
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_CHAR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_INT))){
                        calculated.IntegerLiteral(leftConst.Charliteral() + rightConst.IntegerLiteral());
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_INT)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_CHAR))){
                        calculated.IntegerLiteral(leftConst.IntegerLiteral() + rightConst.Charliteral());
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_CHAR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_CHAR))){
                        calculated.Charliteral((char)(leftConst.Charliteral() + rightConst.Charliteral()));
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_STR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_BOOL))){
                        if(rightConst.BooleanLiteral()){
                            calculated.Stringliteral(leftConst.Stringliteral() + "true");
                        } else {
                            calculated.Stringliteral(leftConst.Stringliteral() + "false");
                        } // else
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_BOOL)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_STR))){
                        if(leftConst.BooleanLiteral()){
                            calculated.Stringliteral("true" + rightConst.Stringliteral());
                        } else {
                            calculated.Stringliteral("false" + rightConst.Stringliteral());
                        } // else
                        binOpExprNode.setConstantValue(calculated);
                    } else if((leftConst.LiteralType().equals(LiteralNode.Literal_STR)
                            && rightConst.LiteralType().equals(LiteralNode.Literal_NULL))){
                        calculated.Stringliteral(leftConst.Stringliteral() + LiteralNode.Literal_NULL);
                        binOpExprNode.setConstantValue(calculated);
                    } else {
                        //SHOULD NOT GET HERE
                        logger.error("Invalid use of constant with operator");
                        throw new ConstantComputeException();
                    } // else
                } // else if
            } // if
        } else { 
            //instance of case
            binOpExprNode.setIsConstant(false);
        } // else
    } // visit(BinOpExprNode)

    @Override
    public void visit(PostFixExprNode postFixExprNode) throws iVisitor.ASTException{
        super.visit(postFixExprNode);
        if((postFixExprNode.Primary() != null) && (postFixExprNode.Primary().PrimaryNoNewArray() != null)){
            if(postFixExprNode.Primary().PrimaryNoNewArray().Literal() != null){
                postFixExprNode.setIsConstant(true);
                postFixExprNode.setConstantValue(postFixExprNode.Primary().PrimaryNoNewArray().Literal());
            } else if ((postFixExprNode.Primary().PrimaryNoNewArray().Expression() != null) &&
                    (postFixExprNode.Primary().PrimaryNoNewArray().Expression().isConstant())){
                postFixExprNode.setIsConstant(true);
                postFixExprNode.setConstantValue(postFixExprNode.Primary().PrimaryNoNewArray().Expression().getConstant());
            } else if ((postFixExprNode.Primary().PrimaryNoNewArray().Literal() != null)){
                postFixExprNode.setIsConstant(true);
                postFixExprNode.setConstantValue(postFixExprNode.Primary().PrimaryNoNewArray().Literal());
            } // else if
        } // if
    } // visit(PostFixExprNode)

    @Override
    public void visit(CastExprNode castExprNode) throws iVisitor.ASTException{
        super.visit(castExprNode);
        if(castExprNode.UnaryExpression() != null){
            if(castExprNode.UnaryExpression().isConstant()){
                castExprNode.setIsConstant(true);
                castExprNode.setConstantValue(castExprNode.UnaryExpression().getConstant());
            } // if
        } // if
    } // visit(CastExprNode)

    @Override
    public void visit(AssignmentExprNode assignmentExprNode) throws iVisitor.ASTException{
        super.visit(assignmentExprNode);
        if(assignmentExprNode.Assignment() == null){
            if((assignmentExprNode.ConditionalExpression() != null) && (assignmentExprNode.ConditionalExpression().isConstant())) {
                assignmentExprNode.setIsConstant(true);
                assignmentExprNode.setConstantValue(assignmentExprNode.ConditionalExpression().getConstant());
            } // if
        } // if
    } // visit(AssignmentExprNode)
} // class ConstantComputeVisitor
