/*******************************************************************************
 * InitializationChecker.java
 * 
 * A module implementing initialization checker.
 * ****************************************************************************/
package cs444.group9.AST.Visitor;

import cs444.group9.AST.Node.Declaration.LocalVariableDeclNode;
import cs444.group9.AST.Node.NameNode;

public class InitializationChecker extends DefaultVisitor{

    boolean _inInitializer;
    LocalVariableDeclNode _currentInitializedvar;

    /*******************************************************************************
     * InitializationChecker Constructor
     * tinme: O(1)
     * *****************************************************************************/ 
    public InitializationChecker(){
        super();
        _inInitializer=false;
        _currentInitializedvar=null;
    } // InitializationChecker()

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(LocalVariableDeclNode localVariableDeclNode) throws iVisitor.ASTException{
        if(localVariableDeclNode.Type() != null){
            visit(localVariableDeclNode.Type());
        } // if
        if(localVariableDeclNode.VariableInitializer() != null){
            _inInitializer=true;
            _currentInitializedvar=localVariableDeclNode;
            visit(localVariableDeclNode.VariableInitializer());
            _inInitializer=false;
            _currentInitializedvar=null;
        } // if
        if(localVariableDeclNode.VariableInitializer().getType() == null){ //check if local variable is declared
            System.err.println("Local variable :" + localVariableDeclNode.VariableDeclaratorId() + " was not initialized.");
            throw new InitializationException();
        } // if
    } // visit(LocalVariableDeclNode)

    @Override
    public void visit(NameNode nameNode) throws iVisitor.ASTException{
        if(_inInitializer && nameNode.getLinkedDeclaration().equals(_currentInitializedvar)){
            System.err.println("Local variable :" + nameNode.toString() + " may not initialize to itself.");
            throw new InitializationException();
        } // if
    } // visit(NameNode)
} // class InitializationChecker
