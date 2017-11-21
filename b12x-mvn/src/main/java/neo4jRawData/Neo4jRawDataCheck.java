/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4jRawData;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;


/**
 *
 * @author kaeaton
 */
public class Neo4jRawDataCheck {
    
    private final String locus;
    private final String fileLocation;
    private boolean fileExists = false;
    private boolean currentDate = false;
    
    public Neo4jRawDataCheck(String incomingLocus, String fileLoc) throws IOException {
        locus = incomingLocus;
        fileLocation = fileLoc;
    }
    
    public boolean dateCheck(Path path) throws IOException {
        try {
            Date date = new Date();
            
//            if (date )
            
            
            
            
        } catch (Exception ex) {
            System.out.println(ex); 
        }
        
        return currentDate;
    }
    
    public boolean checkForFile() throws IOException {
        try {
            // possible file paths
            Path defaultPath = Paths.get(System.getProperty("user.home") 
                    + System.getProperty("file.separator") + "Documents" 
                    + System.getProperty("file.separator") 
                    + "neo4j_" + locus + "_Download.csv");
            Path localPath = Paths.get("." 
                    + System.getProperty("file.separator") 
                    + "neo4j_" + locus + "_Download.csv");
            Path submittedPath = Paths.get(fileLocation);
            
            // check and see if the file exists at default location
            if (Files.exists(defaultPath)) {
                
                // make sure it's a readable file
                if (Files.isRegularFile(defaultPath)) {
                    // path is regular file
                }
                
                 // check date of file
                Date date = new Date();
            }
            
            // check and see if file exists local to program
            else if (Files.exists(localPath)) {
                fileExists = true;
                
                // make sure it's a readable file
                if (Files.isRegularFile(localPath)) {
                    // path is regular file
                }
                
                 // check date of file
                Date date = new Date();
            }
            
            // check and see if file exists at a submitted path
            else if (Files.exists(submittedPath)) {
                fileExists = true;
                
                // make sure it's a readable file
                if (Files.isRegularFile(submittedPath)) {
                    // path is regular file
                }
                
                 // check date of file
                Date date = new Date();
            }

            // if the file doesn't exist or is too old make a new one
            if (fileExists != true){
                // create file
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        // We've found or created the file
        return true;
    }
}
