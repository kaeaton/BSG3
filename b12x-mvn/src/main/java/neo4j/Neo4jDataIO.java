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

/**
 *
 * @author kaeaton
 */
public class Neo4jDataIO {
    public Neo4jDataIO(){
        
    }
    
    public void readCSVFile(File file) throws IOException {
        try {
            
            String line = "";
            String csvSplitBy = ",";
            
            // Read the File
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] gfeAlleles = line.split(csvSplitBy);

                System.out.println("GFE Allele: " + gfeAlleles[1]);

            }
            
            br.close();
            
        } catch (Exception ex) {
            System.out.println(ex); 
        }
    }
    
}
