/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

//import java.util.regex.Pattern;
//import java.util.regex.Matcher;

/**
 *
 * @author kaeaton
 */
public class Neo4jLocusC {
    
    private String locusCRegex = "^HLA-Cw(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(\\d+)-(\\d+)-(\\d+)$";
    
    private String locusCRegexTest = "^HLA-Cw(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(1)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(\\d+)-(\\d+)-(\\d+)$";
    
    public Neo4jLocusC () {
        
    }
    
    // HLA-C Regex
    //          5'    E1    I1    E2    I2    E3    I3    E4    I4    E5    I5
    //          1     2     3     4     5     6     7     8     9     10    11
    // ^HLA-Cw(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)
    
    //           E6    I6    E7    I7    E8    3'   
    //           12    13    14    15    16    17
    //        -(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)$
    int i = 0;
    
    public boolean parseLocus(String gfe) {
        if (gfe.matches(locusCRegexTest)){
            return true;
        }
        return false;
    }
    
    
}
