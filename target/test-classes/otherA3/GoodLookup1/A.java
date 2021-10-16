

public class A extends B{
    public A(){

        String h = 1+ foo().f;
        Object o = bar()[4].field;
    }
    public B[] bar(){
        return new B[5];
    }
}