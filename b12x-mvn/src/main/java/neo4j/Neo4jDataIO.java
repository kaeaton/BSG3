/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import b12x.B12xGUI;
import b12x.WriteFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kaeaton
 */
public class Neo4jDataIO {
    public Neo4jDataIO(){
        
    }
    
    public String RegexToGFE(String locus, String regex){
        String headerSearchString = locus;
        
        //find the w or anything else that might be there
        Pattern p = Pattern.compile(locus + "\\w+");
            
            

        return headerSearchString;
    }
    
    
    public void readCSVFile(String locus, File file, String regex, String searchString) throws IOException {
        try {
            
            String line,
                   csvSplitBy = ",",
                   dataType = B12xGUI.buttonGroupNeo4jOutput
                                     .getSelection().getActionCommand(),
                   timeStamp = LocalDateTime.now().toString();
            boolean writeToFile = B12xGUI.jCheckBoxNeo4jSaveToFile.isSelected();          
            
            System.out.println(locus);
            System.out.println("Made it to DataIO: " + regex);
            
            // Read the File
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileDate = br.readLine();
            String version = br.readLine();
            
            // Headers
            B12xGUI.neo4jResults.append("File generated at: " + timeStamp);
            B12xGUI.neo4jResults.append(System.lineSeparator());
            B12xGUI.neo4jResults.append("Data source: http://neo4j.b12x.org - ");
            B12xGUI.neo4jResults.append(locus + " data downloaded: " + fileDate);
            B12xGUI.neo4jResults.append(System.lineSeparator());
            B12xGUI.neo4jResults.append("Search parameters: " + searchString);
            B12xGUI.neo4jResults.append(System.lineSeparator());
            B12xGUI.neo4jResults.append("IMGT/HLA Database Version " + version);
            B12xGUI.neo4jResults.append(System.lineSeparator());

            // Write the data
            int i = 0;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] gfeAlleles = line.split(csvSplitBy);
                
                // Run the GFE portion through the parser
                if (gfeAlleles[1].matches(regex)){
                    
                    switch (dataType){
                        case "CSV":
                            Neo4jDataFormat.csvFormat(line);
                            break;
                        case "TSV":
                            Neo4jDataFormat.tsvFormat(line);
                            break;
                        case "txt":
                            Neo4jDataFormat.prettyFormat(line);
                            break;
                    }
                    
                    i++;
                }
            }
            
            // Footer
            if (i == 0){
                B12xGUI.neo4jResults.append("No results found");
            } else {
                B12xGUI.neo4jResults.append("Total Results: " + i);
            }

            // Close the buffer
            br.close();
            
            // Write contents of textbox to file
            if(writeToFile){
                WriteFile.writeToFile(locus, version, dataType);
            }
        } catch (Exception ex) {
            System.out.println(ex); 
        }
    }
}
