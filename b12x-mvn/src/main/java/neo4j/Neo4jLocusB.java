/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author kaeaton
 */
public class Neo4jLocusB {
    
    private String locusBRegex = "^HLA-Bw(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)$";
    
    private String locusBRegexTest = "^HLA-Bw(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(1)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)$";
    
    public Neo4jLocusB () {
        
    }
    
    // HLA-B Regex
    //          5'    E1    I1    E2    I2    E3    I3    E4    I4    E5    I5
    //          1     2     3     4     5     6     7     8     9     10    11
    // ^HLA-Bw(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)
    
    //           E6    I6    3'   
    //           12    13    14
    //        -(\d+)-(\d+)-(\d+)$
    
    public boolean parseLocus(String gfe) {
        if (gfe.matches(locusBRegexTest)){
            return true;
        }
        return false;
    }
    
    
}
