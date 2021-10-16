/*******************************************************************************
 * FilePathFilter.java
 * 
 * A module implementing the file path filtering.
 * ****************************************************************************/

package cs444.group9.Filter;

public class FilePathFilter {
    /*******************************************************************************
     * Filters the path name
     * time : O(|name|)
     * *****************************************************************************/ 
    public String filter(String name){
        int idx = name.replaceAll("\\\\", "/").lastIndexOf("/");
        return (idx >= 0) ? name.substring(idx + 1) : name;
    } // filter()

    /*******************************************************************************
     * Determines path name without suffix
     * time : O(|name|)
     * *****************************************************************************/ 
    public String getFileNameWithoutSuffix(String name){
        String filtered=filter(name);
        return filtered.substring(0, filtered.lastIndexOf("."));
    } // getFileNameWithoutSuffix()
} // class FilePathFilter
