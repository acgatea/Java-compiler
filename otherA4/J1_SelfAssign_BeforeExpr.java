//From class notes: this is apparently allowed
public class J1_SelfAssign_BeforeExpr {

    public J1_SelfAssign_BeforeExpr() {}

    public static int test() {
	int a = (a = 5) + a;
	return 123;
    }
}
