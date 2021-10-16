// return types of overriden/overriding methods don't match
package returnPack;

public class B implements A{
    public B(){}
    public int g;
    public char foo(int z){
        return 'h';
    }
}