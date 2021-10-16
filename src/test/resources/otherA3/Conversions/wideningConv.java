// all of the following widening  conversions are allowed
import java.io.*;
public class wideningConv implements Cloneable{
    public wideningConv(){
        wideningConv c = new wideningConv();
        Object o = new wideningConv();
        Cloneable cl = new wideningConv();
        int [] ar = null;
        wideningConv n = null;
        Cloneable n2 = null;
        Object ob = new wideningConv[2];
        Serializable s = new char [7];
        Object oarr = new char [4];
        Cloneable carr = new char [7];
        java.lang.String str = null;
        String y = "stuff" + true + 4 + 'f' + oarr + null;
        java.lang.String z = 5 + str;
        boolean b = true && false || (true | true & !false) & (y == "");
    }
}