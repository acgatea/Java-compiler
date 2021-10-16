
public class D{
    public int a = 2;
    public D(){
        int [] arr = new int[3];
        int a = foo().a ;
    }
    public D foo(){
        return new D();
    }
}