import java.io.*;

public class Main{

	public Main(){ }

	public static int test(){
		int[] myArray=new int[1];
		Serializable a=	new int[3];
		a[0]=123;
		myArray[0]=123;
		Cloneable b=new int[3];
		Cloneable c=new A[3];
		Serializable d=new A[3];
		A[] e=new A[3];
		Cloneable f=(Cloneable)e;
		Serializable g=(Serializable)e;
		return myArray[0];
	}
	
}
