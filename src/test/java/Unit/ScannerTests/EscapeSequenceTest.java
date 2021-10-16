package Unit.ScannerTests; //TODO: change the package name
import cs444.group9.Scanner.*;
import java.util.*;


import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value = Parameterized.class)
public class EscapeSequenceTest {

    public InputScanner inputScanner;

    private String original;
    private String expected;

    // Inject via constructor
    public EscapeSequenceTest(String original, String expected) {
        this.original = original;
        this.expected = expected;
    }

    @Before
    public void setup(){
        inputScanner=new InputScanner();
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameterized.Parameters(name = "expandEscape({0}) => {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"\"int a=5;\"", "int a=5;"},
                {"\" stuf\\t g\\b %\\f* \\r\\nU\\\\W cs\\b end\\n\"", " stuf\t g\b %\f* \r\nU\\W cs\b end\n"},
                {"\"string: \\\"nonempty\\\" and char \\'\\\\\\'\"","string: \"nonempty\" and char \'\\\'" },
                {"\"i \\0 \\15k \\26545 \\7 \\22\"", "i \0 \15k \26545 \7 \22"},
                {"\"K\\512 \\79 \\0000 \\009 \\421\"", "K\512 \79 \0000 \009 \421"}
        });
    }

    @Test
    public void singleLineComment_Test() {
        String result=inputScanner.expandEscape(original);
        assertThat(result).isEqualTo(expected);
    }
}
