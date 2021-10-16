public class J1_NestedWhile1 {
	public J1_NestedWhile1(){ }
	
	public static int test(){
		int ret=0;
		while(ret <= 123){
			ret = ret + 1;
			while(ret % 10 > 0){
				ret = ret + 2;
				while (ret % 5 > 0){
				    ret = ret + 1;
				}
			}
		}
		ret = ret -7;
		return ret;
	}
}
