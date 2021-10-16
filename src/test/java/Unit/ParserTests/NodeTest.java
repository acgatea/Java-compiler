package Unit.ParserTests; //TODO: change the package name
import cs444.group9.Parser.*;
import cs444.group9.Scanner.*;
import java.util.*;


import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {

    // tests that accepting states are accepting
    @Test
    public void isEOF_Test(){
        Token tokEOF = new Token("EOF",0,0);
        tokEOF.getKind(State.ST_EOF, "EOF");
        Node n1 = new Node(tokEOF);
        assertThat(n1.isEOF()).isTrue();

        Token tokBOF = new Token("BOF",0,0);
        tokBOF.getKind(State.ST_BOF, "BOF");
        Node n2 = new Node(tokBOF);
        assertThat(n2.isEOF()).isFalse();
    }

    // tests that symbol is stored and returned correctly
    @Test
    public void getSymbol_Test(){
        Token tok = new Token("\"cs UW\"",0,0);
        tok.getKind(State.ST_STRINGLIT, "\"cs UW\"");
        Node n1 = new Node(tok);
        assertThat(n1.getSymbol()).isEqualTo("StringLiteral");

        List <Node> empty = new ArrayList <Node>();
        Node n2 = new Node("Expression", empty);
        assertThat(n2.getSymbol()).isEqualTo("Expression");
    }

    // tests that children are stored and retrieved correctly
    @Test
    public void getChildren_Test(){
        Token tok1 = new Token("\"cs UW\"",0,0);
        tok1.getKind(State.ST_STRINGLIT, "\"cs UW\"");
        Node n1 = new Node(tok1);
        Token tok2 = new Token("123324",0,0);
        tok2.getKind(State.ST_NUM, "123324");
        Node n2 = new Node(tok2);
        List <Node> child = new ArrayList<Node>();
        child.add(n1);
        child.add(n2);
        Node root = new Node("Root", child);

        List <Node> result = n1.getChildren();
        assertThat(result.size()).isEqualTo(0);
        result = root.getChildren();
        assertThat(result.size()).isEqualTo(2);
        ListIterator<Node> itr = result.listIterator();
        Node expected = itr.next();
        assertThat(expected.getSymbol()).isEqualTo(n1.getSymbol());
        expected = itr.next();
        assertThat(expected.getSymbol()).isEqualTo(n2.getSymbol());
    }
    // tests that list of modifiers are stored and retrieved correctly
    @Test
    public void getModifiers_Test(){
        Token tok = new Token("\"cs UW\"",0,0);
        tok.getKind(State.ST_STRINGLIT, "\"cs UW\"");
        Node n = new Node(tok);

        List <String> result = n.getModifiers();
        assertThat(result.size()).isEqualTo(0);
        result.add("PUBLIC");
        result.add("PROTECTED");
        n.setModifiers(result);
        result = n.getModifiers();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isEqualTo("PUBLIC");
        assertThat(result.get(1)).isEqualTo("PROTECTED");
    }
}