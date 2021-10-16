package Unit.ASTTests;

import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.Declaration.FieldDeclNode;
import cs444.group9.AST.Node.Declaration.MethodDeclNode;
import cs444.group9.AST.Node.Declaration.VariableDeclNode;
import cs444.group9.AST.Node.Environments.FieldData;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldDataTest {
    // test creation of fieldData
    @Test
    public void createField_Test() {
        FieldDeclNode n = new FieldDeclNode(new Node("FieldDecl", new ArrayList<Node>()));

        TypeNode typeInt = new TypeNode(new Node("Type", new ArrayList<Node>()));
        BasicTypeNode basicType = new BasicTypeNode(new Node("BasicType", new ArrayList<Node>()));
        basicType.setElement("int");
        typeInt.BasicType(basicType);
        n.Type(typeInt);

        ModifierNode modPublic = new ModifierNode(new Node("Modifier", new ArrayList<Node>()));
        modPublic.Modifier("PUBLIC");
        ModifierNode modStatic = new ModifierNode(new Node("Modifier", new ArrayList<Node>()));
        modStatic.Modifier("STATIC");

        VariableDeclNode var = new VariableDeclNode(new Node("VariableDeclarator", new ArrayList<Node>()));
        var.VariableDeclarator("myField");
        n.VariableDeclarator(var);

        FieldData m = new FieldData(n);
        assertThat(m.isPublic()).isFalse();
        assertThat(m.isStatic()).isFalse();
        assertThat(m.getType()).isEqualTo(typeInt);
        assertThat(m.getFieldName()).isEqualTo("myField");

        n.addModifier(modPublic);
        m = new FieldData(n);
        assertThat(m.isPublic()).isTrue();
        assertThat(m.isStatic()).isFalse();

        n.addModifier(modStatic);
        m = new FieldData(n);
        assertThat(m.isPublic()).isTrue();
        assertThat(m.isStatic()).isTrue();
    }
}
