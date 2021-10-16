/*******************************************************************************
 * CommentsFilter.java
 * 
 * A module implementing the filtering of the input for comments.
 * ****************************************************************************/

package cs444.group9.Filter;

import java.util.ArrayList;
import java.util.List;

public class CommentsFilter {
    private boolean _startmulticomment;

    /*******************************************************************************
     * Getter for _startmulticomment
     * time : O(1)
     * *****************************************************************************/          
    public boolean isMulti(){
        return _startmulticomment;
    } // isMulti()

    /*******************************************************************************
     * Setter for _startmulticomment
     * time : O(1)
     * *****************************************************************************/     
    public void setMulti(boolean flag){
        _startmulticomment=flag;
    } // setMulti()

    /*******************************************************************************
     * Toggler for _startmulticomment
     * time : O(1)
     * *****************************************************************************/     
    public void toggleMulti(){
        _startmulticomment = (_startmulticomment ) ? false : true;
    } // toggleMulti()

    /*******************************************************************************
     * Filters each line in unfiltered for comments
     * time : O(|input|)
     * *****************************************************************************/     
    public String[] FilterLines(String[] unfiltered){
        List<String> filtered = new ArrayList<String>();
        for(String line: unfiltered){
            filtered.add(FilterLine(line));
        }; // for

        return filtered.stream().toArray(String[]::new);
    } // FilterLines()

    /*******************************************************************************
     * Filters unfiltered for comments
     * time : O(|unfiltered|)
     * *****************************************************************************/    
    public String FilterLine(String unfiltered){
        //NIGHTMARE REGEX
        //does not cover the cases below
        String filtered=unfiltered.replaceAll( "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/", "$1 " );
        //check for other cases
        int endMarkerIdx=filtered.indexOf("*/");
        int startMarkerIdx=filtered.indexOf("/*");
        if(_startmulticomment){
            if (endMarkerIdx != -1){
                filtered=filtered.substring(endMarkerIdx);
                filtered=filtered.replace("*/"," ");
                if (filtered != ""){
                    _startmulticomment=false;
                } // if
                startMarkerIdx=filtered.indexOf("/*");
            } else { 
                // IN THE MIDDLE OF A MULTILINE COMMENT
                filtered="";
            } // else
        } else {
            if (startMarkerIdx != -1){
                _startmulticomment=true;
                filtered=filtered.substring(0,startMarkerIdx+2);
                filtered=filtered.replace("/*"," ");
            } // if
        } // else
        return filtered;
    } // FilterLine()

} // class CommentsFilter
