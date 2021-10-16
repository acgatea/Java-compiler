// REACHABILITY,CODE_GENERATION
public class J1_while2 {
    public J1_while2() {}
    public int m(int x) {

    	while (x>0) {
            int y = x;
            x = y-2;
            /*y=y-1;
            x = y;
            x = x - 1;*/
        }

	return x;
    }
    public static int test() {
	return new J1_while2().m(17)+123;
    }
}


