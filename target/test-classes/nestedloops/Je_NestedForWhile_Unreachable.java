public class Je_NestedForWhile_Unreachable {
	public Je_NestedForWhile_Unreachable(){ }
	
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
