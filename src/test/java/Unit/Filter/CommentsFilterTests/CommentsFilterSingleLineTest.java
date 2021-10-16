package Unit.Filter.CommentsFilterTests;

import static org.assertj.core.api.Assertions.*;

import cs444.group9.Filter.CommentsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class CommentsFilterSingleLineTest {

    public CommentsFilter commentsFilter;

    private String unfiltered;
    private String expected;

    // Inject via constructor
    public CommentsFilterSingleLineTest(String unfiltered, String expected) {
        this.unfiltered = unfiltered;
        this.expected = expected;
    }

    @Before
    public void setup(){
        commentsFilter=new CommentsFilter();
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameters(name = "filterLine(\"{0}\") => \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"int a=5;", "int a=5;"},
                {"//this is a comment", " "},
                {"this is a line with //some comments","this is a line with  " },
                {"Comments go after//","Comments go after "},
                {"//"," "},
                {"This is only a single slash/","This is only a single slash/"},
                {"/*DoubleSlash*/"," "},
                {"stuff /*followed by comments*/","stuff  "},
                {"zero/*all comments*/","zero "},
                {"in/*blahblah*/t","in t"}
        });
    }

    @Test
    public void singleLineComment_Test() {
        String filtered=commentsFilter.FilterLine(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }


}
