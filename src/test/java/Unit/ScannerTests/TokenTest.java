package Unit.ScannerTests;
import cs444.group9.Scanner.*;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TokenTest {
    public Token token;

    @Before
    public void setup(){
        token=new Token("",1,1);
    }

    // simple setLastTokenMinus test
    @Test
    public void lastTokenMinus_Test(){
        assertThat(token.isLastTokenMinus()).isFalse();
        token.setLastTokenMinus(true);
        assertThat(token.isLastTokenMinus()).isTrue();
        token.setLastTokenMinus(false);
        assertThat(token.isLastTokenMinus()).isFalse();
    }

    // tests if setLastTokenMinus works correctly (after getKind is called)
    @Test
    public void LastTokenMinus_getKind_Test(){
        assertThat(token.isLastTokenMinus()).isFalse();
        token.getKind(State.ST_MINUS, "-");
        assertThat(token.isLastTokenMinus()).isTrue();
        token.getKind(State.ST_NUM, "0");
        assertThat(token.isLastTokenMinus()).isFalse();
    }

    // tests that State, lexeme pairs that should give an error give an error
    @Test
    public void getKindError_Test(){
        token.setLastTokenMinus(false);
        String expected = "ERROR";
        String result = token.getKind(State.ST_NUM, "2147483648");
        assertThat(result).isEqualTo(expected);
        token.setLastTokenMinus(true);
        result = token.getKind(State.ST_NUM, "2147483649");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "break");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "case");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "catch");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "const");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "continue");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "default");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "do");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "double");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "finally");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "float");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "goto");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "long");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "private");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "strictfp");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "super");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "switch");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "synchronized");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "throw");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "throws");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "try");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "transient");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "volatile");
        assertThat(result).isEqualTo(expected);
    }

    // tests that State, lexeme pairs that are valid return the expected kind
    @Test
    public void getKindValid_Test() {
        // numbers
        token.setLastTokenMinus(true);
        String expected = "IntegerLiteral";
        String result = token.getKind(State.ST_NUM, "2147483648");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ZERO, "0");
        assertThat(result).isEqualTo(expected);

        // other non-id states
        expected = "COMMA";
        result = token.getKind(State.ST_COMMA, ",");
        assertThat(result).isEqualTo(expected);
        expected = "SEMICOLON";
        result = token.getKind(State.ST_SEMICOLON, ";");
        assertThat(result).isEqualTo(expected);
        expected = "COLON";
        result = token.getKind(State.ST_COLON, ":");
        assertThat(result).isEqualTo(expected);
        expected = "LPAREN";
        result = token.getKind(State.ST_LPAREN, "(");
        assertThat(result).isEqualTo(expected);
        expected = "RPAREN";
        result = token.getKind(State.ST_RPAREN, ")");
        assertThat(result).isEqualTo(expected);
        expected = "LBRACK";
        result = token.getKind(State.ST_LBRACK, "[");
        assertThat(result).isEqualTo(expected);
        expected = "RBRACK";
        result = token.getKind(State.ST_RBRACK, "]");
        assertThat(result).isEqualTo(expected);
        expected = "LBRACE";
        result = token.getKind(State.ST_LBRACE, "{");
        assertThat(result).isEqualTo(expected);
        expected = "RBRACE";
        result = token.getKind(State.ST_RBRACE, "}");
        assertThat(result).isEqualTo(expected);
        expected = "DOT";
        result = token.getKind(State.ST_DOT, ".");
        assertThat(result).isEqualTo(expected);
        expected = "MINUS";
        result = token.getKind(State.ST_MINUS, "-");
        assertThat(result).isEqualTo(expected);
        expected = "PLUS";
        result = token.getKind(State.ST_PLUS, "+");
        assertThat(result).isEqualTo(expected);
        expected = "STAR";
        result = token.getKind(State.ST_STAR, "*");
        assertThat(result).isEqualTo(expected);
        expected = "SLASH";
        result = token.getKind(State.ST_SLASH, "/");
        assertThat(result).isEqualTo(expected);
        expected = "PCT";
        result = token.getKind(State.ST_PCT, "%");
        assertThat(result).isEqualTo(expected);
        expected = "EXCLAM";
        result = token.getKind(State.ST_EXCLAM, "!");
        assertThat(result).isEqualTo(expected);
        expected = "NE";
        result = token.getKind(State.ST_NE, "!=");
        assertThat(result).isEqualTo(expected);
        expected = "BECOMES";
        result = token.getKind(State.ST_BECOMES, "=");
        assertThat(result).isEqualTo(expected);
        expected = "EQUALS";
        result = token.getKind(State.ST_EQUALS, "==");
        assertThat(result).isEqualTo(expected);
        expected = "LT";
        result = token.getKind(State.ST_LT, "<");
        assertThat(result).isEqualTo(expected);
        expected = "LE";
        result = token.getKind(State.ST_LE, "<=");
        assertThat(result).isEqualTo(expected);
        expected = "GT";
        result = token.getKind(State.ST_GT, ">");
        assertThat(result).isEqualTo(expected);
        expected = "GE";
        result = token.getKind(State.ST_GE, ">=");
        assertThat(result).isEqualTo(expected);
        expected = "AMP";
        result = token.getKind(State.ST_AMP, "&");
        assertThat(result).isEqualTo(expected);
        expected = "AMPAMP";
        result = token.getKind(State.ST_AMPAMP, "&&");
        assertThat(result).isEqualTo(expected);
        expected = "OR";
        result = token.getKind(State.ST_OR, "|");
        assertThat(result).isEqualTo(expected);
        expected = "OROR";
        result = token.getKind(State.ST_OROR, "||");
        assertThat(result).isEqualTo(expected);
        expected = "CharacterLiteral";
        result = token.getKind(State.ST_CHARLIT, "\'h\'");
        assertThat(result).isEqualTo(expected);
        expected = "StringLiteral";
        result = token.getKind(State.ST_STRINGLIT, "\"string CS \"");
        assertThat(result).isEqualTo(expected);

        // keywords
        expected = "EXTENDS";
        result = token.getKind(State.ST_ID, "extends");
        assertThat(result).isEqualTo(expected);
        expected = "IMPLEMENTS";
        result = token.getKind(State.ST_ID, "implements");
        assertThat(result).isEqualTo(expected);
        expected = "STATIC";
        result = token.getKind(State.ST_ID, "static");
        assertThat(result).isEqualTo(expected);
        expected = "IMPORT";
        result = token.getKind(State.ST_ID, "import");
        assertThat(result).isEqualTo(expected);
        expected = "PACKAGE";
        result = token.getKind(State.ST_ID, "package");
        assertThat(result).isEqualTo(expected);
        expected = "PUBLIC";
        result = token.getKind(State.ST_ID, "public");
        assertThat(result).isEqualTo(expected);
        expected = "INTERFACE";
        result = token.getKind(State.ST_ID, "interface");
        assertThat(result).isEqualTo(expected);
        expected = "PROTECTED";
        result = token.getKind(State.ST_ID, "protected");
        assertThat(result).isEqualTo(expected);
        expected = "ABSTRACT";
        result = token.getKind(State.ST_ID, "abstract");
        assertThat(result).isEqualTo(expected);
        expected = "NATIVE";
        result = token.getKind(State.ST_ID, "native");
        assertThat(result).isEqualTo(expected);
        expected = "WHILE";
        result = token.getKind(State.ST_ID, "while");
        assertThat(result).isEqualTo(expected);
        expected = "FOR";
        result = token.getKind(State.ST_ID, "for");
        assertThat(result).isEqualTo(expected);
        expected = "BOOLEAN";
        result = token.getKind(State.ST_ID, "boolean");
        assertThat(result).isEqualTo(expected);
        expected = "INT";
        result = token.getKind(State.ST_ID, "int");
        assertThat(result).isEqualTo(expected);
        expected = "CHAR";
        result = token.getKind(State.ST_ID, "char");
        assertThat(result).isEqualTo(expected);
        expected = "BYTE";
        result = token.getKind(State.ST_ID, "byte");
        assertThat(result).isEqualTo(expected);
        expected = "NULL";
        result = token.getKind(State.ST_ID, "null");
        assertThat(result).isEqualTo(expected);
        expected = "THIS";
        result = token.getKind(State.ST_ID, "this");
        assertThat(result).isEqualTo(expected);
        expected = "BooleanLiteral";
        result = token.getKind(State.ST_ID, "true");
        assertThat(result).isEqualTo(expected);
        result = token.getKind(State.ST_ID, "false");
        assertThat(result).isEqualTo(expected);
        expected = "INSTANCEOF";
        result = token.getKind(State.ST_ID, "instanceof");
        assertThat(result).isEqualTo(expected);
        expected = "CLASS";
        result = token.getKind(State.ST_ID, "class");
        assertThat(result).isEqualTo(expected);
        expected = "IF";
        result = token.getKind(State.ST_ID, "if");
        assertThat(result).isEqualTo(expected);
        expected = "ELSE";
        result = token.getKind(State.ST_ID, "else");
        assertThat(result).isEqualTo(expected);
        expected = "FINAL";
        result = token.getKind(State.ST_ID, "final");
        assertThat(result).isEqualTo(expected);
        expected = "NEW";
        result = token.getKind(State.ST_ID, "new");
        assertThat(result).isEqualTo(expected);
        expected = "RETURN";
        result = token.getKind(State.ST_ID, "return");
        assertThat(result).isEqualTo(expected);
        expected = "VOID";
        result = token.getKind(State.ST_ID, "void");
        assertThat(result).isEqualTo(expected);

        // id
        expected = "ID";
        result = token.getKind(State.ST_ID, "New");
        assertThat(result).isEqualTo(expected);
        // bof, eof
        expected = "BOF";
        result = token.getKind(State.ST_BOF, "BOF");
        assertThat(result).isEqualTo(expected);
        expected = "EOF";
        result = token.getKind(State.ST_EOF, "EOF");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void returnKind_Test(){
        token.getKind(State.ST_ID, "int");
        assertThat(token.returnKind()).isEqualTo("INT");
        token.getKind(State.ST_ZERO, "0");
        assertThat(token.returnKind()).isEqualTo("IntegerLiteral");
    }
}
