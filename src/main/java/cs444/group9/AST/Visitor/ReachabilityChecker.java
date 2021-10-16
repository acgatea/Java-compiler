/*******************************************************************************
 * ReachabilityChecker.java
 * 
 * A module implementing the reachability checker.
 * ****************************************************************************/


package cs444.group9.AST.Visitor;

import cs444.group9.AST.Node.Body.CompilationUnitNode;
import cs444.group9.AST.Node.Body.ConstructorBodyNode;
import cs444.group9.AST.Node.Body.MethodBodyNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.Declaration.ClassDeclNode;
import cs444.group9.AST.Node.Declaration.ImportDeclNode;
import cs444.group9.AST.Node.Declaration.LocalVariableDeclNode;
import cs444.group9.AST.Node.LiteralNode;
import cs444.group9.AST.Node.Statement.*;
import cs444.group9.AST.Node.Type.TypeNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReachabilityChecker extends DefaultVisitor {

    private static final Logger logger = LogManager.getLogger(ReachabilityChecker.class);

    boolean _reachable;

    /*******************************************************************************
     * ReachabilityChecker Constructor
     * time: O(1)
     * *****************************************************************************/ 
    public ReachabilityChecker(){
        logger.debug("In constructor of reachabilitychecker");
        _reachable=true;
    } // ReachabilityChecker()

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(CompilationUnitNode node) throws ASTException{
        //DEBUGGING POINT
        logger.debug("Processing class:" + node.getEnv().getScopePair().getName());
        super.visit(node);
    } // visit(CompilationUnitNode)

    @Override
    public void visit(ClassDeclNode node) throws iVisitor.ASTException{
        logger.debug("In classdeclnode");
        if(!_reachable){
            logger.error("Class Declaration is not reachable.");
            throw new ReachabilityException();
        } // if
        super.visit(node);
        _reachable=true;
    } // visit(ClassDeclNode)

    @Override
    public void visit(ConstructorBodyNode constructorBodyNode) throws iVisitor.ASTException{
        logger.debug("In constructorbodynode");
        if(!_reachable){
            logger.error("Constructor body is not reachable.");
            throw new ReachabilityException();
        } // if
        super.visit(constructorBodyNode);
        _reachable=true;
    } // visit(ConstructorBodyNode)

    @Override
    public void visit(LocalVariableDeclNode localVariableDeclNode) throws iVisitor.ASTException{
        logger.debug("In localvariabledeclnode");
        if(!_reachable){
            logger.error("Local variable declaration is not reachable.");
            throw new ReachabilityException();
        } // if
        super.visit(localVariableDeclNode);
        _reachable=true;
    } // visit(LocalVariableDeclNode)

    @Override
    public void visit(WhileStmtNode whileStmtNode) throws iVisitor.ASTException{
        boolean afterStmtReachable=true;
        logger.debug("In whilestmtnode");
        if(!_reachable){
            logger.error("While Statement is not reachable.");
            throw new ReachabilityException();
        } // if
        if(whileStmtNode.Expression() != null){
            visit(whileStmtNode.Expression());
            if(whileStmtNode.Expression().isConstant()){
                LiteralNode constVal=whileStmtNode.Expression().getConstant();
                if(constVal.LiteralType().equals(LiteralNode.Literal_BOOL)){
                    if(!constVal.BooleanLiteral()){
                        logger.error("While statement body is not reachable");
                        throw new ReachabilityException();
                    } else {
                        afterStmtReachable=false;
                    } // else
                } // if
            } // if
        } // if
        if (whileStmtNode.Statement() != null){
            visit(whileStmtNode.Statement());
        } // if
        if(!afterStmtReachable){
            _reachable=false;
        } else {
            _reachable=true;
        } // else
    } // visit(WhileStmtNode)

    @Override
    public void visit(ReturnStmtNode returnStmtNode) throws iVisitor.ASTException{
        logger.debug("inside returnstmtnode");
        if(!_reachable){ 
            // no reachable break statement
            logger.error("Return statement is not reachable");
            throw new ReachabilityException();
        } // if
        if(returnStmtNode.Expression() != null){
            visit(returnStmtNode.Expression());
        } // if
        _reachable=false;
    } // visit(ReturnStmtNode)

    @Override
    public void visit(ForStmtNode forStmtNode) throws iVisitor.ASTException{
        boolean afterStmtReachable=true;
        logger.debug("inside forstmtnode");
        if(!_reachable){ 
            // no reachable break statement
            logger.error("For statement is not reachable");
            throw new ReachabilityException();
        } // if
        if(forStmtNode.Expression() != null){
            visit(forStmtNode.Expression());
            if(forStmtNode.Expression().isConstant()){
                if(forStmtNode.Expression().isConstant()){
                    LiteralNode constVal=forStmtNode.Expression().getConstant();
                    if(constVal.LiteralType().equals(LiteralNode.Literal_BOOL)){
                        if(!constVal.BooleanLiteral()){
                            logger.error("Unable to reach body of for loop statement");
                            throw new ReachabilityException();
                        } else {
                            afterStmtReachable=false;
                        } // else
                    } // if
                } // if
            } else {
                afterStmtReachable=true;
            } // else
        } else { 
            // no expression
            afterStmtReachable=false;
        }
        visit(forStmtNode.Statement());
        if(forStmtNode.Forupdate() != null){
            if(!_reachable){
                logger.error("For Update is not reachable");
                throw new ReachabilityException();
            } // if
        } // if
        if(!afterStmtReachable){
            _reachable=false;
        } else {
            _reachable=true;
        } // else
    } // visit(ForStmtNode)

    @Override
    public void visit(MethodBodyNode methodBodyNode) throws iVisitor.ASTException{
        logger.debug("inside methodbodynode");
        if(!_reachable){ //no reachable break statement
            logger.error("Method body is not reachable");
            throw new ReachabilityException();
        } // if
        super.visit(methodBodyNode);
        if(methodBodyNode.getEnv().getScopePair().getDeclaration() instanceof MethodHeaderNode){
            MethodHeaderNode methodHeaderNode=(MethodHeaderNode)methodBodyNode.getEnv().getScopePair().getDeclaration();
            TypeNode returnType=methodHeaderNode.ReturnType();
            if((returnType != null) && (!returnType.isVoid())){
                if((methodBodyNode.Block() != null) && _reachable){
                    logger.error("Missing return statement for non-void type return method.");
                    throw new ReachabilityException();
                } // if
            } // if
        } // if
        _reachable=true;
    } // visit(MethodBodyNode)

    @Override
    public void visit(IfStmtNode ifStmtNode) throws iVisitor.ASTException{
        logger.debug("inside ifstmtnode");
        if(!_reachable){ 
            // no reachable break statement
            logger.error("If Statement is not reachable");
            throw new ReachabilityException();
        } // if
        if(ifStmtNode.Expression() != null){
            visit(ifStmtNode.Expression());
        } // if

        if(ifStmtNode.Statements().size() == 1){
            visit(ifStmtNode.Statements().get(0));
            _reachable=true;
        } else if (ifStmtNode.Statements().size() == 2){
            visit(ifStmtNode.Statements().get(0));
            boolean temp_reachable;
            temp_reachable=_reachable;
            _reachable=true;
            visit(ifStmtNode.Statements().get(1));
            _reachable= _reachable || temp_reachable;
        } // else if
    } // visit(IfStmtNode)

    @Override
    public void visit(NoTrailingStmtNode noTrailingStmtNode) throws iVisitor.ASTException {
        logger.debug("inside notrailingstmtnode");
        if(!_reachable){
            logger.error("Statement is not reachable");
            throw new ReachabilityException();
        } // if
        super.visit(noTrailingStmtNode);
    } // visit(NoTrailingStmtNode)

    public void visit(EmptyStmtNode emptyStmtNode) throws iVisitor.ASTException {
        logger.debug("inside emptystmtnode");
        if(!_reachable){
            logger.error("Statement is not reachable");
            throw new ReachabilityException();
        } // if
        super.visit(emptyStmtNode);
    } // visit(EmptyStmtNode)
} // class ReachabilityChecker
