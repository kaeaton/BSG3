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
            System.out.println(p);
            
            Matcher m = p.matcher(locus);
            System.out.println(m);
            
            if (m.find()) {
                locusParser = "Neo4jLocus" + m.group(1);
                System.out.println(locusParser);
            }
            
            Class<?> clazz = Class.forName("neo4j." + locusParser);
            Constructor<?> ctor = clazz.getConstructor();
            Object parser = ctor.newInstance();
            
            // Read the File
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] gfeAlleles = line.split(csvSplitBy);
                parser.parseLocus(gfeAlleles[1]);
                
                
                
//                System.out.println("GFE Allele: " + gfeAlleles[1]);

            }
            
            br.close();
            
        } catch (Exception ex) {
            System.out.println(ex); 
        }
    }
    
}
