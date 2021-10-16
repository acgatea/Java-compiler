public class J1_NestedWhile_Unreachable_Inner {
	public J1_NestedWhile_Unreachable_Inner(){ }
	
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
