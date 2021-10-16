import pkg.*;
import pack.*;

public class Main implements A,B,C,D,E,F{
	public Main(){ }

	public static int test(){
		return 123;	
	}

	public int FuncA(){
		return 0;	
	}

	public int FuncB(){
		return FuncA();	
	}

	public int FuncC(){
		return FuncB();
	}

	public int FuncD(){
		return FuncC() + FuncA();	
	}

	public int FuncE(){
		return FuncD() + FuncF();	
	}

	public int FuncF(){
		return 1;	
	}
}
