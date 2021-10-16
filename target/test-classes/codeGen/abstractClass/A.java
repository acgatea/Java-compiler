
public  class A extends B implements C{
    public A(){}

    public int foo(){
        return 14;
    }

    public static int test(){
        A a = new A();
        B b = new A();
        C c = new A();
        if(c.bar() != 16) return 96;
        if(c.foo() != 14) return 97;
        if(c.ccc() != 27) return 98;
        if(b.bar() != 16) return 99;
        if(b.foo() != 14) return 100;
        if(b.ccc() != 27) return 101;
        if(a.foo() != 14) return 102;
        if(a.bar() != 16) return 103;
        if(a.ccc() != 27) return 104;


        return 123;
    }

}