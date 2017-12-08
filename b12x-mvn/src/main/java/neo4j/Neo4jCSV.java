/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import b12x.B12xGUI;

/**
 *
 * @author katrinaeaton
 */
public class Neo4jCSV {
    
    public Neo4jCSV(){
        
    }
    
    static void writeCSV(boolean toFile, String line){
        B12xGUI.neo4jResults.append(line);
        B12xGUI.neo4jResults.append(System.lineSeparator());
    }
    
}
