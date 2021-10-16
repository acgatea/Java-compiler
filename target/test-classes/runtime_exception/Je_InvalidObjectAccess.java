public class Je_InvalidObjectAccess{
	public Je_InvalidObjectAccess(){ }

	public static int test(){
		String s=null;
		return s.charAt(0);	
	}
}
