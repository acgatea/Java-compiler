public class Main {
	public Main() { }

	public static int test(){
		B b=new BImpl();
		if(b instanceof A){
			return 123;
		} else {
			return 124;		
		}
	}
}
