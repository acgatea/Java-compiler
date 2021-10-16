import java.io.*;

public class Main {
	public Main() { }

	public static int test(){
		A a=new A();
		String s="asdf" + a;
		String t=a+"asdf";
		int[] myArray=new int[3];
		String u= a + "asdf" + null + true + 123 + myArray;
		System.out.print(s);
		System.out.print(t);
		if (u.equals((Object)new String("Some random objectasdfnulltrue123Some random object"))){
			return 123;		
		}
		return 124; 	
	}
}
