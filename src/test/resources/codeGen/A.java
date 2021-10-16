/*
public class A{
    public A(){
        int y = 2;
        y = 13 + y - 29;
    }
    public static int test(){
        return 3;
    }
}*/

//J1_300locals
public class A {
    public A() { }

    public static int test() {
        Object [] arr = new Object[3];
        arr[0] = (Object) arr;
        return 123;
    }
}
