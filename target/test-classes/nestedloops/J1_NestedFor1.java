public class J1_NestedFor1 {
	public J1_NestedFor1(){ }
	
	public static int test(){
		int ret=0;
		for(int i=0;i<2;i=i+1){
			for(int j=0;j<3;j=j+1){
				ret = ret + 20;			
			}		
		}
		ret = ret +3;
		return ret;	
	}
}
