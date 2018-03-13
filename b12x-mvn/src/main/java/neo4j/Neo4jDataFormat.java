/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import b12x.B12xGUI;
import b12x.WriteFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author katrinaeaton
 */
public class Neo4jDataFormat {
    
    static String csvSplitBy = ",";
    static File destinationFile;
    
    public Neo4jDataFormat(){
        
    }
    
    static void csvFormat(String line){
        B12xGUI.neo4jResults.append(line);
        B12xGUI.neo4jResults.append(System.lineSeparator());
    }
    
    static void tsvFormat(String line){
        String[] gfeAllele = line.split(csvSplitBy);
        B12xGUI.neo4jResults.append(gfeAllele[0]+ "\t" + gfeAllele[1]);
        B12xGUI.neo4jResults.append(System.lineSeparator());

    }
    
    static void prettyFormat(String line){
        String[] gfeAllele = line.split(csvSplitBy);
        B12xGUI.neo4jResults.append(String.format("%-25s", gfeAllele[0]));
        B12xGUI.neo4jResults.append(gfeAllele[1]);
        B12xGUI.neo4jResults.append(System.lineSeparator());
    }

}
