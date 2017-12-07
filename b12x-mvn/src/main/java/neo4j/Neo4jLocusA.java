/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;




/**
 *
 * @author kaeaton
 */
public class Neo4jLocusA {
    
    private String locusARegex = "^HLA-Aw(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(\\d+)-(\\d+)-(\\d+)$";
    
    private String locusARegexTest = "^HLA-Aw(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(1)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)-(\\d+)"
            + "-(\\d+)-(\\d+)-(\\d+)$";
    
    public Neo4jLocusA () {
        
    }
    
    // HLA-A Regex
    //          5'    E1    I1    E2    I2    E3    I3    E4    I4    E5    I5
    //          1     2     3     4     5     6     7     8     9     10    11
    // ^HLA-Aw(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)
    
    //           E6    I6    E7    I7    E8    3'   
    //           12    13    14    15    16    17
    //        -(\d+)-(\d+)-(\d+)-(\d+)-(\d+)-(\d+)$
    

    
    public boolean parseLocus(String gfe) {
        if (gfe.matches(locusARegexTest)){
            return true;
        }
        return false;
    }
    
    
}
