package Unit.ASTTests;

import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Node.Declaration.ConstructorDeclNode;
import cs444.group9.AST.Node.Declaration.ConstructorDeclarationNode;
import cs444.group9.AST.Node.Declaration.MethodDeclNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;

import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import org.junit.Test;
import cs444.group9.AST.Node.Environments.MethodData;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MethodDataTest {

    // test creation of methodData for a method
    @Test
    public void createMethod_Test(){
        MethodHeaderNode n = new MethodHeaderNode(new Node("MethodHeader", new ArrayList<Node>() ));
        MethodHeaderNode n2 = new MethodHeaderNode(new Node("MethodHeader", new ArrayList<Node>() ));

        TypeNode typeInt = new TypeNode(new Node("Type", new ArrayList<Node>() ));
        BasicTypeNode basicType = new BasicTypeNode(new Node("BasicType", new ArrayList<Node>() ));
        basicType.setElement("int");
        typeInt.BasicType(basicType);
        TypeNode typeInt2 = new TypeNode(new Node("Type", new ArrayList<Node>() ));
        BasicTypeNode basicType2 = new BasicTypeNode(new Node("BasicType", new ArrayList<Node>() ));
        basicType2.setElement("int");
        typeInt2.BasicType(basicType2);

        ModifierNode modPublic = new ModifierNode(new Node("Modifier", new ArrayList<Node>() ));
        modPublic.Modifier("PUBLIC");
        ModifierNode modStatic = new ModifierNode(new Node("Modifier", new ArrayList<Node>() ));
        modStatic.Modifier("STATIC");

        MethodDeclNode declaration1 = new MethodDeclNode(new Node("MethodDeclarator", new ArrayList<Node>() ));
        declaration1.Identifier("foo");
        FormalParameterNode param1 = new FormalParameterNode(new Node("FormalParameter", new ArrayList<Node>() ));
        param1.VariableDeclaratorId("p1");
        param1.Type(typeInt);
        FormalParameterNode param2 = new FormalParameterNode(new Node("FormalParameter", new ArrayList<Node>() ));
        param2.Type(typeInt2);
        param2.VariableDeclaratorId("p1");
        n.MethodDeclarator(declaration1);

        MethodDeclNode declaration2 = new MethodDeclNode(new Node("MethodDeclarator", new ArrayList<Node>() ));
        declaration2.Identifier("second");
        n2.MethodDeclarator(declaration2);
        n2.addModifier(modPublic);
        n2.addModifier(modStatic);

        try {
            MethodData m = new MethodData(n);
            assertThat(m.isAbstract()).isFalse();
            assertThat(m.isPublic()).isFalse();
            assertThat(m.isStatic()).isFalse();
            assertThat(m.isNative()).isFalse();
            assertThat(m.isFinal()).isFalse();
            assertThat(m.isConstructor()).isFalse();
            assertThat(m.isVoid()).isTrue();
            assertThat(m.getReturnType()).isNull();
            assertThat(m.getMethodName()).isEqualTo("foo");
            m.setAbstract(true);
            assertThat(m.isAbstract()).isTrue();
            assertThat(m.isPublic()).isFalse();
            assertThat(m.isSameSignature(m)).isTrue();

            MethodData m2 = new MethodData(n2);
            assertThat(m2.isAbstract()).isFalse();
            assertThat(m2.isPublic()).isTrue();
            assertThat(m2.isStatic()).isTrue();
            assertThat(m2.isNative()).isFalse();
            assertThat(m2.isFinal()).isFalse();
            assertThat(m2.isConstructor()).isFalse();
            assertThat(m2.isVoid()).isTrue();
            assertThat(m2.getReturnType()).isNull();
            assertThat(m2.getMethodName()).isEqualTo("second");
            assertThat(m2.isSameSignature(m2)).isTrue();
            assertThat(m.isSameSignature(m2)).isFalse(); // different name

            // same name
            declaration2.Identifier("foo");
            m2 = new MethodData(n2);
            assertThat(m.isSameSignature(m2)).isTrue();
            // same name but not same return type
            n2.ReturnType(typeInt);
            m2 = new MethodData(n2);
            assertThat(m2.isVoid()).isFalse();
            assertThat(m2.getReturnType() == typeInt).isTrue();
            assertThat(m.isSameSignature(m2)).isTrue();

            // add an int parameter to m2
            List<FormalParameterNode> paramList = new ArrayList<>();
            paramList.add(param1);
            m2.setParams(paramList);
            assertThat(m.isSameSignature(m2)).isFalse();
            m.setParams(paramList);
            // both have same parameter, same type
            assertThat(m.isSameSignature(m2)).isTrue();
            // both have 1 parameter of same name and same type name (but different type object)
            List<FormalParameterNode> paramList2 = new ArrayList<>();
            paramList2.add(param2);
            m.setParams(paramList2);
            assertThat(m.isSameSignature(m2)).isTrue();
            // both have 1 parameter of different name (but same signature)
            param2.VariableDeclaratorId("p2");
            paramList2.remove(0);
            paramList2.add(param2);
            m.setParams(paramList2);
            assertThat(m.isSameSignature(m2)).isTrue();
        } catch(iVisitor.ASTException e){
            assertThat(false).isTrue();
        }

        // catches duplicate parameter name (even if not the same type)
        try {
            List<FormalParameterNode> paramList = new ArrayList<>();
            basicType2.setElement("char");
            typeInt2.BasicType(basicType2);
            param1.VariableDeclaratorId("p1");
            param1.Type(typeInt);
            param2.VariableDeclaratorId("p1");
            param2.Type(typeInt2);
            declaration1.addFormalParameter(param1);
            declaration1.addFormalParameter(param2);
            n.MethodDeclarator(declaration1);

            MethodData m3 = new MethodData(n);
            assertThat(true).isFalse();

        } catch(iVisitor.ASTException e){
            assertThat(true).isTrue();
        }
    }

    // test creation of methodData for a constructor (same test as above, with a constructor)
    @Test
    public void createConstructor_Test(){
        ConstructorDeclarationNode n = new ConstructorDeclarationNode(new Node("ConstructorDeclaration", new ArrayList<Node>() ));
        ConstructorDeclarationNode n2 = new ConstructorDeclarationNode(new Node("ConstructorDeclaration", new ArrayList<Node>() ));

        TypeNode typeInt = new TypeNode(new Node("Type", new ArrayList<Node>() ));
        BasicTypeNode basicType = new BasicTypeNode(new Node("BasicType", new ArrayList<Node>() ));
        basicType.setElement("int");
        typeInt.BasicType(basicType);
        TypeNode typeInt2 = new TypeNode(new Node("Type", new ArrayList<Node>() ));
        BasicTypeNode basicType2 = new BasicTypeNode(new Node("BasicType", new ArrayList<Node>() ));
        basicType2.setElement("int");
        typeInt2.BasicType(basicType2);

        ModifierNode modPublic = new ModifierNode(new Node("Modifier", new ArrayList<Node>() ));
        modPublic.Modifier("PUBLIC");
        ModifierNode modStatic = new ModifierNode(new Node("Modifier", new ArrayList<Node>() ));
        modStatic.Modifier("STATIC");

        ConstructorDeclNode declaration1 = new ConstructorDeclNode(new Node("ConstructorDecl", new ArrayList<Node>() ));
        declaration1.Identifier("foo");
        FormalParameterNode param1 = new FormalParameterNode(new Node("FormalParameter", new ArrayList<Node>() ));
        param1.VariableDeclaratorId("p1");
        param1.Type(typeInt);
        FormalParameterNode param2 = new FormalParameterNode(new Node("FormalParameter", new ArrayList<Node>() ));
        param2.Type(typeInt2);
        param2.VariableDeclaratorId("p1");
        n.ConstructorDeclarator(declaration1);

        ConstructorDeclNode declaration2 = new ConstructorDeclNode(new Node("ConstructorDecl", new ArrayList<Node>() ));
        declaration2.Identifier("second");
        n2.ConstructorDeclarator(declaration2);
        n2.addModifier(modPublic);
        n2.addModifier(modStatic);

        try {
            MethodData m = new MethodData(n);
            assertThat(m.isAbstract()).isFalse();
            assertThat(m.isPublic()).isFalse();
            assertThat(m.isStatic()).isFalse();
            assertThat(m.isNative()).isFalse();
            assertThat(m.isFinal()).isFalse();
            assertThat(m.isConstructor()).isTrue();
            assertThat(m.isVoid()).isFalse();
            assertThat(m.getReturnType()).isNull();
            assertThat(m.getMethodName()).isEqualTo("foo");
            m.setAbstract(true);
            assertThat(m.isAbstract()).isTrue();
            assertThat(m.isPublic()).isFalse();
            assertThat(m.isSameSignature(m)).isTrue();

            MethodData m2 = new MethodData(n2);
            assertThat(m2.isAbstract()).isFalse();
            assertThat(m2.isPublic()).isTrue();
            assertThat(m2.isStatic()).isTrue();
            assertThat(m2.isNative()).isFalse();
            assertThat(m2.isFinal()).isFalse();
            assertThat(m2.isConstructor()).isTrue();
            assertThat(m2.isVoid()).isFalse();
            assertThat(m2.getReturnType()).isNull();
            assertThat(m2.getMethodName()).isEqualTo("second");
            assertThat(m2.isSameSignature(m2)).isTrue();
            assertThat(m.isSameSignature(m2)).isFalse(); // different name

            // same name
            declaration2.Identifier("foo");
            m2 = new MethodData(n2);
            assertThat(m.isSameSignature(m2)).isTrue();

            // add an int parameter to m2
            List<FormalParameterNode> paramList = new ArrayList<>();
            paramList.add(param1);
            m2.setParams(paramList);
            assertThat(m.isSameSignature(m2)).isFalse();
            m.setParams(paramList);
            // both have same parameter, same type
            assertThat(m.isSameSignature(m2)).isTrue();
            // both have 1 parameter of same name and same type name (but different type object)
            List<FormalParameterNode> paramList2 = new ArrayList<>();
            paramList2.add(param2);
            m.setParams(paramList2);
            assertThat(m.isSameSignature(m2)).isTrue();
            // both have 1 parameter of different name (but same signature)
            param2.VariableDeclaratorId("p2");
            paramList2.remove(0);
            paramList2.add(param2);
            m.setParams(paramList2);
            assertThat(m.isSameSignature(m2)).isTrue();
        } catch(iVisitor.ASTException e){
            assertThat(false).isTrue();
        }

        // catches duplicate parameter name (even if not the same type)
        try {
            List<FormalParameterNode> paramList = new ArrayList<>();
            basicType2.setElement("char");
            typeInt2.BasicType(basicType2);
            param1.VariableDeclaratorId("p1");
            param1.Type(typeInt);
            param2.VariableDeclaratorId("p1");
            param2.Type(typeInt2);
            declaration1.addFormalParameterNode(param1);
            declaration1.addFormalParameterNode(param2);
            n.ConstructorDeclarator(declaration1);

            MethodData m3 = new MethodData(n);
            assertThat(true).isFalse();

        } catch(iVisitor.ASTException e){
            assertThat(true).isTrue();
        }
    }


}