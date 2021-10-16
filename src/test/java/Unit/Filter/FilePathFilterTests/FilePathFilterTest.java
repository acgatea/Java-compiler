package Unit.Filter.FilePathFilterTests;

import static org.assertj.core.api.Assertions.*;

import cs444.group9.Filter.FilePathFilter;
import org.junit.Before;
import org.junit.Test;

public class FilePathFilterTest {
    public FilePathFilter _filePathFilter;

    @Before
    public void setup(){
        _filePathFilter=new FilePathFilter();
    }

    @Test
    public void filterUnixFileTest(){
        String unfiltered="/a/foo/foo.java";
        String expected="foo.java";

        String filtered=_filePathFilter.filter(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }

    @Test
    public void filterWindowsFileTest(){
        String unfiltered="C:\\a\\foo\\foo.java";
        String expected="foo.java";

        String filtered=_filePathFilter.filter(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }

    @Test
    public void filterFileNoPathOnlyTest(){
        String unfiltered="foo.java";
        String expected="foo.java";

        String filtered=_filePathFilter.filter(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }

    @Test
    public void getFileNameWithoutSuffixUnixPathTest(){
        String unfiltered="/a/foo/foo.java";
        String expected="foo";

        String filtered=_filePathFilter.getFileNameWithoutSuffix(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }

    @Test
    public void getFileNameWithoutSuffixNoPathTest(){
        String unfiltered="foo.java";
        String expected="foo";

        String filtered=_filePathFilter.getFileNameWithoutSuffix(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }

}
