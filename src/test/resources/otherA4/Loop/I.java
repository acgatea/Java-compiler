
public class I{
    public I(){}
    public int foo(){
        for(int y = 2; (y = 5-1)+1 == 5;){
            // reachable
        }
        //reachable
        return 2;
    }
}