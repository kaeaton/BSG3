/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import b12x.B12xGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    
    static void readCSVFile(String locus, File file, String regex) throws IOException {
        try {
            
            String locusParser = "";
            String line = "";
            String csvSplitBy = ",";
            
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
            
            // Headers
            String timeStamp = LocalDateTime.now().toString();
            B12xGUI.neo4jResults.append("File generated at: " + timeStamp);
            B12xGUI.neo4jResults.append(System.lineSeparator());
            B12xGUI.neo4jResults.append("Data source: http://neo4j.b12x.org - ");
            B12xGUI.neo4jResults.append(locus + " data downloaded: " + br.readLine());
            B12xGUI.neo4jResults.append(System.lineSeparator());
            
            
            // Skip date stamp
//            br.readLine();
            
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] gfeAlleles = line.split(csvSplitBy);
                
                // Run the GFE portion through the parser
//                boolean results = (boolean)parse.invoke(gfeParser, gfeAlleles[1]);
                if (gfeAlleles[1].matches(regex)){
                    System.out.println(line);
                    B12xGUI.neo4jResults.append(line);
                    B12xGUI.neo4jResults.append(System.lineSeparator());

                }
            }
            
            // Close the buffer
            br.close();
            
        } catch (Exception ex) {
            System.out.println(ex); 
        }
    }
}
