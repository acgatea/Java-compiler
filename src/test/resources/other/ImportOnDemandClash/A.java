// should resolve B using current package other.B

//import pack.B.*;


package other;
import pack.*;
import pack2.*;

public class A{
    public A(B b){}
}