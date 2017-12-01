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
 * @author katrinaeaton
 */
public class Neo4jRegexGroupSwapper {
    
    
    private String searchString;
    
    public Neo4jRegexGroupSwapper(){
        
    }

    public String createSearchRegex(String incomingRegex, int regexGroup, String type){
        searchString = "(.*)($" + regexGroup + ")(.*)";
        String replacedValue = incomingRegex.replaceAll(searchString,"$1" + type + "$3");
        System.out.println(replacedValue);
        
        return replacedValue;
    }
}