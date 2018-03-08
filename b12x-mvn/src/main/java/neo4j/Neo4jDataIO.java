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
    
    public void readCSVFile(String locus, File file, String regex) throws IOException {
        try {
            
            String locusParser = "";
            String line = "";
            String csvSplitBy = ",";
            String fileDate;
            String dataType = B12xGUI.buttonGroupNeo4jOutput
                                     .getSelection().getActionCommand();
            String destinationFile = WriteFile.fileWriter(locus, dataType);
            String timeStamp = LocalDateTime.now().toString();
            boolean writeToFile = B12xGUI.jCheckBoxNeo4jSaveToFile.isSelected();
            BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile, true));
            
            
            
            System.out.println(locus);
            System.out.println("Made it to DataIO: " + regex);

//            // Parse the locus to call class (no "-" allowed in class name)
//            Pattern p = Pattern.compile("^HLA-(\\w+)$");
//            Matcher m = p.matcher(locus);
//            
//            // Create class name
//            if (m.find()) {
//                locusParser = "Neo4jLocus" + m.group(1);
//                System.out.println(locusParser);
//            }
//            
//            // Tell it that the name is a class and instantiate class
//            Class<?> parser = Class.forName("neo4j." + locusParser);
//            Constructor<?> ctr = parser.getConstructor();
//            Object gfeParser = ctr.newInstance();
//            
//            // Tell it that the parseLocus method exists
//            Method parse = parser.getDeclaredMethod("parseLocus", String.class);
            
            // Read the File
            BufferedReader br = new BufferedReader(new FileReader(file));
            fileDate = br.readLine();
            // Create output write file
            if(writeToFile){
//                destinationFile = WriteFile.fileWriter(locus, dataType);
//                bw = new BufferedWriter(new FileWriter(destinationFile, true));
                bw.append("File generated at: " + timeStamp);
                bw.append(System.lineSeparator());
                bw.append("Data source: http://neo4j.b12x.org - ");
                bw.append(locus + " data downloaded: " + fileDate);
                bw.append(System.lineSeparator());
            }
            
            
            // Headers
            B12xGUI.neo4jResults.append("File generated at: " + timeStamp);
            B12xGUI.neo4jResults.append(System.lineSeparator());
            B12xGUI.neo4jResults.append("Data source: http://neo4j.b12x.org - ");
            B12xGUI.neo4jResults.append(locus + " data downloaded: " + fileDate);
            B12xGUI.neo4jResults.append(System.lineSeparator());
            
//            System.out.println(B12xGUI.buttonGroupNeo4jOutput.getSelection().getActionCommand());
            
            int i = 0;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] gfeAlleles = line.split(csvSplitBy);
                
                // Run the GFE portion through the parser
//                boolean results = (boolean)parse.invoke(gfeParser, gfeAlleles[1]);
                if (gfeAlleles[1].matches(regex)){
                    
//                    switch (B12xGUI.buttonGroupNeo4jOutput.getSelection().getActionCommand()){
                    switch (dataType){
                        case "CSV":
                            Neo4jDataFormat.csvFormat(writeToFile, line, bw);
                            break;
                        case "TSV":
                            Neo4jDataFormat.tsvFormat(writeToFile, line, bw);
                            break;
                        case "txt":
                            Neo4jDataFormat.prettyFormat(writeToFile, line, bw);
                            break;
                    }

//                    B12xGUI.neo4jResults.append(line);
//                    B12xGUI.neo4jResults.append(System.lineSeparator());
                    i++;

                }
            }
            if (i == 0){
                B12xGUI.neo4jResults.append("No results found");
            } else {
                B12xGUI.neo4jResults.append("Total Results: " + i);
            }

            // Close the buffers
            br.close();
            bw.close();
            
        } catch (Exception ex) {
            System.out.println(ex); 
        }
    }
}
