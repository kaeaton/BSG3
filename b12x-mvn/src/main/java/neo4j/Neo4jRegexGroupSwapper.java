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
        searchString = "(.*)(%" + regexGroup + ")(.*)";
        String replacedValue = incomingRegex.replaceAll(searchString,("$1" + type + "$3"));
        System.out.println(replacedValue);
        
        return replacedValue;
    }
    
    public static String replaceGroup(String regex, String source, int groupToReplace, int groupOccurrence, String replacement) {
        Matcher m = Pattern.compile(regex).matcher(source);
        for (int i = 0; i < groupOccurrence; i++)
            if (!m.find()) return source; // pattern not met, may also throw an exception here
        return new StringBuilder(source).replace(m.start(groupToReplace), m.end(groupToReplace), replacement).toString();
    }
}