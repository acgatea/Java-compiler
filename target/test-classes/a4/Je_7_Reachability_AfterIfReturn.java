// JOOS1:REACHABILITY,UNREACHABLE_STATEMENT
// JOOS2:REACHABILITY,UNREACHABLE_STATEMENT
// JAVAC:UNKNOWN
// 
/**
 * Reachability:
 * - Check that all statements (including empty statements and empty
 * blocks) are reachable.  
 */
public class Je_7_Reachability_AfterIfReturn {

    public Je_7_Reachability_AfterIfReturn () {}

    public static int test(int j) {
	if (j == 0) {
	    return 7;
	    int l = 9;
	}
	return 123;
    }

}
