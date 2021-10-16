package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class InterfaceMemberDeclNode extends ASTNode {
    AbstractMethodDeclNode _abstractMethodDeclaration;

    public InterfaceMemberDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    }

    @Override
    public void print() {
        if(_abstractMethodDeclaration != null){
            _abstractMethodDeclaration.print();
        }
    }

    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    }

    public AbstractMethodDeclNode AbstractMethodDeclaration(){
        return _abstractMethodDeclaration;
    }

    public void AbstractMethodDeclaration(AbstractMethodDeclNode abstractMethodDeclaration){
        _abstractMethodDeclaration=abstractMethodDeclaration;
    }
}
