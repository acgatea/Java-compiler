public class J1_Array_Cast_To_Method {
	public J1_Array_Cast_To_Method() { }

	public static int test(){
		int[] a = new int[3];
		Object b= (Object)a;
		if(b.toString().equals((Object)new String("Some random object"))){
			return 123;
		}
		return 124;
	}
}
