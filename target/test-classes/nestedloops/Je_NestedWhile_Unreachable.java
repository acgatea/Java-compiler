public class Je_NestedWhile_Unreachable {
	public Je_NestedWhile_Unreachable(){ }
	
	public static int test(){
		int ret=0;
		while(ret < 123){
			while(1 == 1){
				ret = ret + 1;			
			}
		}
		return 123;	
	}
}
