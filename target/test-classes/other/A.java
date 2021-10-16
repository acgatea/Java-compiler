package pack;

public class A {
    public A(int p1, int p2){
       // p2 = foo() + 2;
        //char c = bar('t', true);
        int [] a = new int[3];
        p2 = a[1];
        bar('2', false, a);
    }
    public A(){}
    public int field = 1;
    public void voidMethod (){ }

    public char bar(char x1, boolean x2, int [] array){
        A a = new A();
        A [] ar = new A[3];
        a = ar[0];
        return x1;
    }
}