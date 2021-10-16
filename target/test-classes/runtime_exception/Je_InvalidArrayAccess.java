public class Je_InvalidArrayAccess{
	public Je_InvalidArrayAccess(){ }

	public static int test(){
		int[] myArr=new int[3];
		myArr[3]=123;
		return myArr[3];
	}
}
