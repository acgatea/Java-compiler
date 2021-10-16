
public class H{
    public H(){}
    public void foo(){
        for(; "the 4 true null" == "th" + "e" + ' ' + 4 + " " + true + " " + null; ){ // works if you get rid of null
            // reachable
        }
        // unreachable
    }
}