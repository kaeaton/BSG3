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
public class Neo4jLocusA {
    
    private String alleleA1 = "HLA-Aw0-0-0-211-0-1-0-0-0-0-0-0-0-0-0-0-0";
    private String alleleA2 = "HLA-Aw52-8-12-45-19-65-15-10-7-9-12-2-3-2-4-1-24";
    private String alleleA3 = "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-10";
    private String alleleA4 = "HLA-Aw46-1-1-1-1-1-90-1-1-1-1-1-1-1-1-1-7";
    private String[] alleles = {alleleA1, alleleA2, alleleA3, alleleA4};

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
    
    public void parseLocusA(String GFE) {
        for (int i = 0; i < alleles.length; i++){
            if (alleles[i].matches(locusARegexTest)){
                System.out.println(alleles[i]);
            }
        }
    }
    
    
}
