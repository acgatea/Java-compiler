import java.io.*;

public class Main{
	public Main(){ }

	public static int test(){
		A[] a = new A[3];
		Serializable b=(Serializable)a;
		b[0]=123;
		return b[0];	
	}
}
