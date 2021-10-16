public class J1_EscapeChars {
	public J1_EscapeChars(){ }

	public static int test(){
		if("\"foo\"" + "\"bar\\" == "\"foo\"\"bar\\"){
			return 123;		
		} else {
			return 124;		
		}
	}
}
