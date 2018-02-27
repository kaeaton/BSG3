/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import java.time.LocalDate;
/**
 *
 * @author katrinaeaton
 */
public class WriteFile {
    public WriteFile () {
        
    }
    
    public static void fileWriter(String locus, String fileType) {
        
        LocalDate timeStamp = LocalDate.now();
        String fileName = System.getProperty("user.home")
                + System.getProperty("file.separator") + "Documents"
                + System.getProperty("file.separator") + "BSG"
                + System.getProperty("file.separator") 
                + timeStamp + "_" + locus + "." + fileType;
        
        System.out.println(fileName);
    }
    
    
}
