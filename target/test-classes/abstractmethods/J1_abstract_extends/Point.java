public abstract class Point {

	public Point(){ }
	
	public int x=1;
	public int y=1;
	
	public void move(int dx, int dy) {
		x = x+ dx;
		y = y+ dy;
		alert();
	}
	
	public abstract void alert();
}
