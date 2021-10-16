public class J1_NestedFor2 {
	public J1_NestedFor2(){ }
	
	public static int test(){
		int ret=0;
		for(int i=0;i<10;i=i+1){
			ret= ret * 2;
			for(int j=0;j<10;j=j+1){
				ret = ret + 2;			
			}		
		}
		ret = ret -20337;
		return ret;
	}
}
