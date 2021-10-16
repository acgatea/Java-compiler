package Unit.ParserTests;
import cs444.group9.Parser.*;
import cs444.group9.Scanner.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


public class InputParserTest {
    public InputParser inputParser;
    public InputScanner inputScanner;

    @Before
    public void setup(){
        inputScanner=new InputScanner();
        inputParser=new InputParser("res/machine.lr1");
    }

    // tests smallest correct program
    @Test
    public void smallInput_Test(){
        try {
            List<Token> result = inputScanner.scan("public class foo{}");
            Node node = inputParser.parse(result);
            assertThat(node.getSymbol()).isEqualTo("CompilationUnit");

            List<Node> children = node.getChildren();
            assertThat(children.size()).isEqualTo(1);
            node = children.get(0);
            assertThat(node.getSymbol()).isEqualTo("TypeDeclaration");

            children = node.getChildren();
            assertThat(children.size()).isEqualTo(1);
            node = children.get(0);
            assertThat(node.getSymbol()).isEqualTo("ClassDeclaration");

            children = node.getChildren();
            assertThat(children.size()).isEqualTo(4);
            node = children.get(0);
            assertThat(node.getSymbol()).isEqualTo("Modifiers");
            assertThat(node.getChildren().size()).isEqualTo(1);
            node = children.get(1);
            assertThat(node.getSymbol()).isEqualTo("CLASS");
            assertThat(node.getChildren().size()).isEqualTo(0);
            node = children.get(2);
            assertThat(node.getSymbol()).isEqualTo("Identifier");
            assertThat(node.getChildren().size()).isEqualTo(1);
            assertThat(node.getChildren().get(0).getSymbol()).isEqualTo("ID");
            node = children.get(3);
            assertThat(node.getSymbol()).isEqualTo("ClassBody");

            children = node.getChildren();
            assertThat(children.size()).isEqualTo(2);
            node = children.get(0);
            assertThat(node.getSymbol()).isEqualTo("LBRACE");
            node = children.get(1);
            assertThat(node.getSymbol()).isEqualTo("RBRACE");
        } catch (InputScanner.ScanningError e){
            assertThat(false).isTrue();
        } catch (InputParser.ParsingError p){
            assertThat(false).isTrue();
        }
    }

    // tests that input that has  a parse error throws exception
    @Test
    public void parseError_Test(){
        try {
            List<Token> result = inputScanner.scan("class foo();");
            Node node = inputParser.parse(result);

            assertThat(false).isTrue();
        } catch (InputScanner.ScanningError e){
            assertThat(false).isTrue();
        } catch (InputParser.ParsingError p){
            assertThat(true).isTrue();
        }
    }
}
