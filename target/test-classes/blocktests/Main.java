import java.io.PrintStream;

public class Main{
	public Main(){}

	public static int field = 12;


	public int secret(){
	    while(counter < 1000){
	        counter = counter + 1;
	        secretPartner();
        }
	    return 5;
    }

    public int counter = 0;

    public void secretPartner(){
        String s = "";
        while(counter < 1000){
            s = "->" + s + ";" +  secret() + "<-" + counter + "\n";
            {
                ;
            }
            {secret();}
        }
        PrintStream p = new PrintStream();
    }

	public static int other(){
	    int var = 1;
        {
            Object[] ob1 = new Object[50];
            {
                Main m = new Main();
                while(m.secret() == 5) {
                    var= var+1;
                    Object[] ob2 = new Object[50];
                    {
                        Object[] ob3 = new Object[50];
                        for(int i = 0; i < 100; i = Main.bar(i+1)){
                            {
                                if(Main.field != var+1) {
                                    return Main.field = Main.field - var;//3
                                }
                                {
                                    Object[] ob5 = new Boolean[50];
                                    if((ob3 = ob5) == ob5) return 3;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1000;
    }

	public static int bar(int t){
	    int z = 34;
	    Main [] m = new Main[40];
	    if(Main.other() == 3) {
	        return t;
        }
	    return t*1000;
	}

	public static int foo(){
	    int result = 0;
	    if(result != 0) return -4;
        int y1 = 3;
        result = result + Main.bar(22);
        if(result != 22) return -5;
        char [] arr1 = new char[10];
        result = result + Main.bar(19);
        if(result != 41) return -6;

        {
        int y2 = 4;
        result = result + Main.bar(12);
            if(result != 53) return -7;
            char [] arr2 = new char[10];
        {
            if(result != 53) return -8;
            result = result + Main.bar(-6);
            if(result !=47) return -9;

            while(arr2.length< 0){
                result = result + Main.bar(322);
                char[]arr3=new char[10];
            }
            if(result != 47) return -10;
            {
                if(result != 47) return -11;
                result = result + Main.bar(50);
                if(result != 97) return -12;
                String ss = "some cs uw";
        }
            if(result != 97) return -13;
        }
            if(result != 97) return -14;
            result = result + Main.bar(1);
            if(result != 98) return -15;

            for(char[] arr4 = new char[10]; y2 < 6; y2 = y2+1){
        int [] tt = new int[100];
        result = result + Main.bar(13);
        {
        result = result + Main.bar(-5);
        char[]arr5=new char[10];
        result = result + Main.bar(23);
        }
        result = result + Main.bar(-4);
        }
            if(result != 152) return -16;


            int [] tt = new int[100];
        result = result + Main.bar(-14);
            if(result != 138) return -17;

        }
        if(result != 138) return -18;
        result = result + Main.bar(-18);
        if(result != 120) return -19;

        int [] tt2 = new int[100];
        return result+Main.field;
	}

	public static int test(){
        Main.field = 5;

        {
            if((new Main()).secret() != 5) return -1777;
        }
        return Main.foo();
	}
}
