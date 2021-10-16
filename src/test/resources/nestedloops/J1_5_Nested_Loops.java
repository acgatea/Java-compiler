public class J1_5_Nested_Loops{
	public J1_5_Nested_Loops(){ }

	public static int test(){
		int ret=-100;
        for(int a=0;a<50;a=a+2){
            for(int b=a; b< 100;b = b+1){
                for(int c =b;c<2;c=c+1){
                    for(int d=1000;d>c;d=d-1){
                        for(int e=0;e<10;e=e+1){
                            ret=ret +1;
                        }
                    }
                }
            }
        }
		return ret - 29757;	
	}
}
