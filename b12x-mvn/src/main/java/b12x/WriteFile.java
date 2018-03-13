/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author katrinaeaton
 */
public class WriteFile {
    public WriteFile () {
        
    }
    
    public static String fileName(String locus, String fileType) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:a");

        LocalDate dateStamp = LocalDate.now();
        String timeStamp = LocalTime.now().format(dtf)
                                    .toString().replaceAll(":", "-");
        String fileName = System.getProperty("user.home")
                + System.getProperty("file.separator") + "Documents"
                + System.getProperty("file.separator") + "BSG"
                + System.getProperty("file.separator") 
                + dateStamp + "_" + timeStamp + "_"
                + locus + "." + fileType;
        
        System.out.println(fileName);
        
        return fileName;
    }
    
    
}
