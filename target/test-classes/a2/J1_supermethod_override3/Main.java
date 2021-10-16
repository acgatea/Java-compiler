// HIERARCHY
/* Hierarchy:
 * class CompA {
 * }
 * abstract class CompB extends CompA implements Comparable {
 * }
 * class CompC extends CompB {
 *  int compareTo(Object)
 * }
 */
public class Main {
    public Main() {}
    public static int test() {
	Comparable x = new CompB();
	return x.compareTo((Object)null);
    }
}
