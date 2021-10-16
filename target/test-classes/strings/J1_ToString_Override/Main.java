public class Main {
	public Main(){ }

	public static int test(){
		A a=new A();
		String s=a.toString() + a;
		if(s.toString().equals((Object)new String("AA"))){
			return 123;		
		}
		return 124;
	}
}
