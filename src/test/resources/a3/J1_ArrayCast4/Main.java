// TYPE_CHECKING
/*
 * TypeChecking:
 * Main extends A and thus Main[] is assignable to A[]
 */
public class Main extends A {

    public Main(){}

    public static int test() {
	
	A[] a = new Main[42];
	a[0] = new Main();

	int result = a[0].testA();

	return result;
	
    }

    public int testA(){
	return 123;
    }
}
