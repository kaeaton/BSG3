/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4jRawData;

//import java.io.File;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;


/**
 *
 * @author kaeaton
 */
public class Neo4jRawDataDateCheck {
    
    private final String locus;
    private final String fileLocation;
    private boolean fileExists = false;
    private boolean currentDate = false;
    
    public Neo4jRawDataDateCheck(String incomingLocus, String fileLoc) throws IOException {
        locus = incomingLocus;
        fileLocation = fileLoc;
    }
    
    public boolean checkDate(File file) throws IOException {
        try {
            // Set expiration date
            LocalDate expirationDate = LocalDate.now().minus(1, ChronoUnit.MONTHS);
            System.out.println(expirationDate);
            
            // Get file date
            BufferedReader r = new BufferedReader(new FileReader(file));
            String line = r.readLine();
            System.out.println(line);
            r.close();
            LocalDate fileDate = LocalDate.parse(line);
            
            // compare file date to expiration date
            if (fileDate.isBefore(expirationDate)){
                System.out.println("expired");
                return false;
            } else {
                System.out.println("still good");
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex); 
        }
        
        // if it can't figure it out just generate a new file.
        return false;
    }
}
