/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kaeaton
 */
public class Neo4jDataIO {
    public Neo4jDataIO(){
        
    }
    
    public void readCSVFile(String locus, File file) throws IOException {
        try {
            
            String locusParser = "";
            String line = "";
            String csvSplitBy = ",";
            
            System.out.println(locus);

            // Parse the locus to call class (no "-" allowed in class name)
            Pattern p = Pattern.compile("^HLA-(\\w+)$");
            Matcher m = p.matcher(locus);
            
            // Create class name
            if (m.find()) {
                locusParser = "Neo4jLocus" + m.group(1);
                System.out.println(locusParser);
            }
            
            // Tell it that the name is a class and instantiate class
            Class<?> parser = Class.forName("neo4j." + locusParser);
            Constructor<?> ctr = parser.getConstructor();
            Object gfeParser = ctr.newInstance();
            
            // Tell it that the parseLocus method exists
            Method parse = parser.getDeclaredMethod("parseLocus", String.class);
            
            // Read the File
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            // Skip date stamp
            br.readLine();
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] gfeAlleles = line.split(csvSplitBy);
                
                // Run the GFE portion through the parser
                boolean results = (boolean)parse.invoke(gfeParser, gfeAlleles[1]);
                if (results){
                    System.out.println(line);

                }
                
                
                
//                System.out.println("GFE Allele: " + gfeAlleles[1]);

            }
            
            br.close();
            
        } catch (Exception ex) {
            System.out.println(ex); 
        }
    }
    
}
