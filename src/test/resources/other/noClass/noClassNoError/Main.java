// JOOS1: PARSER_WEEDER,JOOS1_INTERFACE,PARSER_EXCEPTION
// JOOS2: HIERARCHY
/*
       boz - f, g
      /   \
    bar   baz
      \   /
       foo
*/

public class Main extends foo{
    public int mainField;

    public Main() {}

    public static int test() {
        foo f = new foo();
        return f.f();
    }

}
