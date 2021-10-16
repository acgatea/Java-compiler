public class J1_NestedForWhile_Unreachable_Inner {
	public J1_NestedForWhile_Unreachable_Inner(){ }
	
	public static int test(){
		int ret=0;
		for(;ret < 123;){
			while(1 == 1){
				ret = ret + 1;			
			}
		}
		return 123;	
	}
}
