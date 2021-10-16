public class Main{
	public Main() { }
	public static int test(){
		boolean[] z = new boolean[3];
		A[] a=new AImpl[3];
		a[0]=new AImpl();
		return a[0].RetOne() + 122;	
	}
}
