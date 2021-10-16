// JOOS1:DISAMBIGUATION,ILLEGAL_FORWARD_FIELD_REFERENCE
// JOOS2:DISAMBIGUATION,ILLEGAL_FORWARD_FIELD_REFERENCE
// JAVAC:UNKNOWN

public class Je_5_ForwardReference_InAssignment2 {
	public Je_5_ForwardReference_InAssignment2() {}
	public int[] arr = new int[4];
	public int b = arr[b] = 0;

	public static Je_5_ForwardReference_InAssignment2 method(int a) {
		return null;
	}
	
	public static int test() {
		return 123;
	}
}
