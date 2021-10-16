package Unit.ScannerTests; //TODO: change the package name
import cs444.group9.Scanner.*;
import java.util.*;


import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class InputScannerTest{

    public InputScanner inputScanner;

    @Before
    public void setup(){
        inputScanner=new InputScanner();
    }

    // tests that input with no tokens returns list with just BOF, EOF
    @Test
    public void noTokens_Test(){
        try {
            List<Token> result = inputScanner.scan("");
            assertThat(result.get(0).returnKind()).isEqualTo("BOF");
            assertThat(result.get(0).returnLexeme()).isEqualTo("BOF");
            assertThat(result.get(1).returnKind()).isEqualTo("EOF");
            assertThat(result.get(1).returnLexeme()).isEqualTo("EOF");
        } catch (InputScanner.ScanningError e){
            assertThat(false).isTrue();
        }
        try {
            List<Token> result = inputScanner.scan("\n     \t    \n\r\r     ");
            assertThat(result.get(0).returnKind()).isEqualTo("BOF");
            assertThat(result.get(0).returnLexeme()).isEqualTo("BOF");
            assertThat(result.get(1).returnKind()).isEqualTo("EOF");
            assertThat(result.get(1).returnLexeme()).isEqualTo("EOF");
        } catch (InputScanner.ScanningError e){
            assertThat(false).isTrue();
        }
    }

    // does it throw exception when input with no valid tokenization?
    // looks at possible errors caught in InputScanner (not by getKind)
    @Test
    public void invalidInput_Exception_Test(){
        // -- yields ST_DECR, which is not accepting
        try {
            List<Token> result = inputScanner.scan("a-- b");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // -- yields ST_INCR, which is not accepting
        try {
            List<Token> result = inputScanner.scan("b++a");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // invalid symbol (no transition from ST_Start)
        try {
            List<Token> result = inputScanner.scan("\n     \t    \n\r\r  ~   ");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // invalid symbol (no transition from ST_Start)
        try {
            List<Token> result = inputScanner.scan("\n     \t    \n\r\r  not@ID   ");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // end of line is reached before the second double quote
        try {
            List<Token> result = inputScanner.scan(" \" end of line\n \" ");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // no valid transition from ST_LSINGCHAR on character (and no previous accepting state)
        try {
            List<Token> result = inputScanner.scan("\'gJ\' ");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // non-octal escape
        try {
            List<Token> result = inputScanner.scan("\'\\412\'");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // non-octal escape
        try {
            List<Token> result = inputScanner.scan("\'\\019\'");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
        // non-octal escape
        try {
            List<Token> result = inputScanner.scan("\'\\1221\'");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(true).isTrue();
        }
    }
    // does it throw exception when getKind returns K_ERR?
    @Test
    public void invalidKind_Exception_Test() {
        // -- yields ST_DECR, which is not accepting
        try {
            List<Token> result = inputScanner.scan(" ... super!");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e) {
            assertThat(true).isTrue();
        }
        try {
            List<Token> result = inputScanner.scan(" bad range -\n2147483648");
            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e) {
            assertThat(true).isTrue();
        }
    }

    // if input is valid, is tokenization correct?
    @Test
    public void validInput_Test() {
        try {
            List<Token> result = inputScanner.scan(" one_liner + int ==true , null.(54)= \n stuff\t\n \" some string@ \\n UW \\' \"!");
            assertThat(result.get(0).returnKind()).isEqualTo("BOF");
            assertThat(result.get(0).returnLexeme()).isEqualTo("BOF");
            assertThat(result.get(1).returnKind()).isEqualTo("ID");
            assertThat(result.get(1).returnLexeme()).isEqualTo("one_liner");
            assertThat(result.get(2).returnKind()).isEqualTo("PLUS");
            assertThat(result.get(2).returnLexeme()).isEqualTo("+");
            assertThat(result.get(3).returnKind()).isEqualTo("INT");
            assertThat(result.get(3).returnLexeme()).isEqualTo("int");
            assertThat(result.get(4).returnKind()).isEqualTo("EQUALS");
            assertThat(result.get(4).returnLexeme()).isEqualTo("==");
            assertThat(result.get(5).returnKind()).isEqualTo("BooleanLiteral");
            assertThat(result.get(5).returnLexeme()).isEqualTo("true");
            assertThat(result.get(6).returnKind()).isEqualTo("COMMA");
            assertThat(result.get(6).returnLexeme()).isEqualTo(",");
            assertThat(result.get(7).returnKind()).isEqualTo("NULL");
            assertThat(result.get(7).returnLexeme()).isEqualTo("null");
            assertThat(result.get(8).returnKind()).isEqualTo("DOT");
            assertThat(result.get(8).returnLexeme()).isEqualTo(".");
            assertThat(result.get(9).returnKind()).isEqualTo("LPAREN");
            assertThat(result.get(9).returnLexeme()).isEqualTo("(");
            assertThat(result.get(10).returnKind()).isEqualTo("IntegerLiteral");
            assertThat(result.get(10).returnLexeme()).isEqualTo("54");
            assertThat(result.get(11).returnKind()).isEqualTo("RPAREN");
            assertThat(result.get(11).returnLexeme()).isEqualTo(")");
            assertThat(result.get(12).returnKind()).isEqualTo("BECOMES");
            assertThat(result.get(12).returnLexeme()).isEqualTo("=");
            assertThat(result.get(13).returnKind()).isEqualTo("ID");
            assertThat(result.get(13).returnLexeme()).isEqualTo("stuff");
            assertThat(result.get(14).returnKind()).isEqualTo("StringLiteral");
            assertThat(result.get(14).returnLexeme()).isEqualTo(" some string@ \n UW \' ");
            assertThat(result.get(15).returnKind()).isEqualTo("EXCLAM");
            assertThat(result.get(15).returnLexeme()).isEqualTo("!");
            assertThat(result.get(16).returnKind()).isEqualTo("EOF");
            assertThat(result.get(16).returnLexeme()).isEqualTo("EOF");
        } catch (InputScanner.ScanningError e) {
            assertThat(false).isTrue();
        }
        try {
            List<Token> result = inputScanner.scan("0-2147483648 !=<=>= boolean<null{32> [$]} * /%&extends &&|true ||false;:\'\\n\'");
            assertThat(result.get(0).returnKind()).isEqualTo("BOF");
            assertThat(result.get(0).returnLexeme()).isEqualTo("BOF");
            assertThat(result.get(1).returnKind()).isEqualTo("IntegerLiteral");
            assertThat(result.get(1).returnLexeme()).isEqualTo("0");
            assertThat(result.get(2).returnKind()).isEqualTo("MINUS");
            assertThat(result.get(2).returnLexeme()).isEqualTo("-");
            assertThat(result.get(3).returnKind()).isEqualTo("IntegerLiteral");
            assertThat(result.get(3).returnLexeme()).isEqualTo("2147483648");
            assertThat(result.get(4).returnKind()).isEqualTo("NE");
            assertThat(result.get(4).returnLexeme()).isEqualTo("!=");
            assertThat(result.get(5).returnKind()).isEqualTo("LE");
            assertThat(result.get(5).returnLexeme()).isEqualTo("<=");
            assertThat(result.get(6).returnKind()).isEqualTo("GE");
            assertThat(result.get(6).returnLexeme()).isEqualTo(">=");
            assertThat(result.get(7).returnKind()).isEqualTo("BOOLEAN");
            assertThat(result.get(7).returnLexeme()).isEqualTo("boolean");
            assertThat(result.get(8).returnKind()).isEqualTo("LT");
            assertThat(result.get(8).returnLexeme()).isEqualTo("<");
            assertThat(result.get(9).returnKind()).isEqualTo("NULL");
            assertThat(result.get(9).returnLexeme()).isEqualTo("null");
            assertThat(result.get(10).returnKind()).isEqualTo("LBRACE");
            assertThat(result.get(10).returnLexeme()).isEqualTo("{");
            assertThat(result.get(11).returnKind()).isEqualTo("IntegerLiteral");
            assertThat(result.get(11).returnLexeme()).isEqualTo("32");
            assertThat(result.get(12).returnKind()).isEqualTo("GT");
            assertThat(result.get(12).returnLexeme()).isEqualTo(">");
            assertThat(result.get(13).returnKind()).isEqualTo("LBRACK");
            assertThat(result.get(13).returnLexeme()).isEqualTo("[");
            assertThat(result.get(14).returnKind()).isEqualTo("ID");
            assertThat(result.get(14).returnLexeme()).isEqualTo("$");
            assertThat(result.get(15).returnKind()).isEqualTo("RBRACK");
            assertThat(result.get(15).returnLexeme()).isEqualTo("]");
            assertThat(result.get(16).returnKind()).isEqualTo("RBRACE");
            assertThat(result.get(16).returnLexeme()).isEqualTo("}");
            assertThat(result.get(17).returnKind()).isEqualTo("STAR");
            assertThat(result.get(17).returnLexeme()).isEqualTo("*");
            assertThat(result.get(18).returnKind()).isEqualTo("SLASH");
            assertThat(result.get(18).returnLexeme()).isEqualTo("/");
            assertThat(result.get(19).returnKind()).isEqualTo("PCT");
            assertThat(result.get(19).returnLexeme()).isEqualTo("%");
            assertThat(result.get(20).returnKind()).isEqualTo("AMP");
            assertThat(result.get(20).returnLexeme()).isEqualTo("&");
            assertThat(result.get(21).returnKind()).isEqualTo("EXTENDS");
            assertThat(result.get(21).returnLexeme()).isEqualTo("extends");
            assertThat(result.get(22).returnKind()).isEqualTo("AMPAMP");
            assertThat(result.get(22).returnLexeme()).isEqualTo("&&");
            assertThat(result.get(23).returnKind()).isEqualTo("OR");
            assertThat(result.get(23).returnLexeme()).isEqualTo("|");
            assertThat(result.get(24).returnKind()).isEqualTo("BooleanLiteral");
            assertThat(result.get(24).returnLexeme()).isEqualTo("true");
            assertThat(result.get(25).returnKind()).isEqualTo("OROR");
            assertThat(result.get(25).returnLexeme()).isEqualTo("||");
            assertThat(result.get(26).returnKind()).isEqualTo("BooleanLiteral");
            assertThat(result.get(26).returnLexeme()).isEqualTo("false");
            assertThat(result.get(27).returnKind()).isEqualTo("SEMICOLON");
            assertThat(result.get(27).returnLexeme()).isEqualTo(";");
            assertThat(result.get(28).returnKind()).isEqualTo("COLON");
            assertThat(result.get(28).returnLexeme()).isEqualTo(":");
            assertThat(result.get(29).returnKind()).isEqualTo("CharacterLiteral");
            assertThat(result.get(29).returnLexeme()).isEqualTo("\n");
            assertThat(result.get(30).returnKind()).isEqualTo("EOF");
            assertThat(result.get(30).returnLexeme()).isEqualTo("EOF");
        } catch (InputScanner.ScanningError e) {
            assertThat(false).isTrue();
        }
        try {
        List<Token> result = inputScanner.scan("\'\\145\'+\'\\77\'*\"\\0001 \\0\\1198 \\54\\\" \\632 \\236K\\009\\26545\\1\"\'\\277\'");
        assertThat(result.get(0).returnKind()).isEqualTo("BOF");
        assertThat(result.get(0).returnLexeme()).isEqualTo("BOF");
        assertThat(result.get(1).returnKind()).isEqualTo("CharacterLiteral");
        assertThat(result.get(1).returnLexeme()).isEqualTo("\145");
        assertThat(result.get(2).returnKind()).isEqualTo("PLUS");
        assertThat(result.get(2).returnLexeme()).isEqualTo("+");
        assertThat(result.get(3).returnKind()).isEqualTo("CharacterLiteral");
        assertThat(result.get(3).returnLexeme()).isEqualTo("\77");
        assertThat(result.get(4).returnKind()).isEqualTo("STAR");
        assertThat(result.get(4).returnLexeme()).isEqualTo("*");
        assertThat(result.get(5).returnKind()).isEqualTo("StringLiteral");
        assertThat(result.get(5).returnLexeme()).isEqualTo("\0001 \0\1198 \54\" \632 \236K\009\26545\1");
        assertThat(result.get(6).returnKind()).isEqualTo("CharacterLiteral");
        assertThat(result.get(6).returnLexeme()).isEqualTo("\277");
        assertThat(result.get(7).returnKind()).isEqualTo("EOF");
        assertThat(result.get(7).returnLexeme()).isEqualTo("EOF");
        } catch (InputScanner.ScanningError e) {
            assertThat(false).isTrue();
        }
    }
}