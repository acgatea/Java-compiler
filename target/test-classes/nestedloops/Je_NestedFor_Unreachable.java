public class Je_NestedFor_Unreachable {
	public Je_NestedFor_Unreachable(){ }
	
	public static int test(){
		int ret=0;
		for(int i=0;i<5;i=i+1){
			for(int j=5;(20 > 19);j=j+1){
				ret = ret + 1;			
			}		
		}
		return 123;	
	}
}
