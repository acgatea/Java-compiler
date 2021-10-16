import java.io.Serializable;

public class A extends E implements B,C,F {
    public int f = 99;
    public static int g = 161;
    public static char[] secretArray = new char[7];
    public A() {
    }
    public int bar() {
        return f - 25;
    }
    public int hashCode(){return 44;}

    public static int[] makeArr() {return new int[5];}
    public int[] makeArr2() {return new int[6];}

    public void useless(){
        int t= 4;
        if(t == 3){
            return;
        } else {
            int q = 99;
            return;
        }
    }

    public static int test() {
        A a = new A();
        E e = new E();

        {
            int zg = 4;
            while(zg < 5){
                a.useless();
                zg = 5;
                int [] jj = new int[19];
            }
        }

        int [] arr = new int[4];
        B [] interfaces = new B[10];
        interfaces[0] = null;
        interfaces[1] = a;


        if(((Object) arr).hashCode() != 42 || ((Object) interfaces).hashCode() != 42) return 11;
        if(((Object) e).hashCode() != 43 || e.hashCode() != 43) return 12;
        if(((Object) a).hashCode() != 44 || ((E) a).hashCode() != 44 || a.hashCode() != 44) return 14;

        if(! (a instanceof A)) return 15;
        if(! (a instanceof Object)) return 16;
        if(! (a instanceof B)) return 17;
        if(! (a instanceof C)) return 18;
        if(! (a instanceof D) ) return 19;
        if(!(arr instanceof int[])) return 20;
        if(!(arr instanceof Cloneable)) return 21;
        if(!(arr instanceof Object)) return 22;
        if(!(arr instanceof java.io.Serializable)) return 23;

        if(((E)a).bar() != 74 || a.bar() != 74) return 124;//a.bar();
        if(e.bar() != 48) return 125;//e.bar();
        if(e.foo() != 98) return 126;//e.foo();
        if(a.f != 99) return 127;//a.f;
        if(e.f != 98) return 128;//a.f;
        if(g != 161) return 129;
        if(A.g != 161) return 130;
        if(E.g != 160) return 131;
        String s = a +";A: " + a;
        if(!s.equals((Object) "Some random object;A: Some random object")) return 132;
        s = "arr: " + arr;
        if(!s.equals((Object) "arr: Some random object")) return 133;
        s = "" + s+ "; interfaces: " + interfaces;
        if(!s.equals((Object) "arr: Some random object; interfaces: Some random object")) return 134;

        B b1 = new A();
        if(b1.bar() != 74 || b1.hashCode() != 44) return 135;
        F b2 = new E();
        if(b2.foo() != 98) return 136;
        if(b2.bar() != 48) return 137;
        if(b2.hashCode() != 43) return 138;
        if(! (b1 instanceof A) || !(b2 instanceof  E) ) return 139;

        arr[0] = 57;
        Cloneable cl = arr;
        Object ob = arr.clone();
        int[] arr2 = (int []) arr.clone();
        if(arr2 != arr || arr2.hashCode() != 42) return 140;
        arr2 = (int[]) A.makeArr().clone();
        if(arr2.hashCode() != 42) return 141;
        char [] arr3 = (char[]) ((char []) A.secretArray.clone()).clone();
        if(arr3 != A.secretArray || arr3.hashCode() != 42 || A.makeArr().clone().hashCode() != 42) return 142;
        arr2 = (int[]) a.makeArr2().clone();
        if(arr2.hashCode() != 42) return 143;

        B [] cloneArray = (B[]) interfaces.clone();
        if(cloneArray != interfaces || cloneArray.hashCode() != 42 || ((Object) interfaces).hashCode() != 42) return 144;

        if(A.makeArr().length != 5) return 173;
        if(A.secretArray.length != 7) return 174;
        if(a.makeArr2().length != 6) return 175;
        e = a;
        if(interfaces[1].bar() != 74) return 176;
        if(e.f != 98) return 177;
        if(! (((A) e) instanceof D)) return 178;
        return 123;
    }
}