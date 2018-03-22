/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    
    public static String fileName(String locus, String version, String fileType) 
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss");

        LocalDate dateStamp = LocalDate.now();
        String timeStamp = LocalTime.now().format(dtf).toString();
        String fileName = System.getProperty("user.home")
                + System.getProperty("file.separator") + "Documents"
                + System.getProperty("file.separator") + "BSG"
                + System.getProperty("file.separator") + locus 
//                + ".v" + version.replaceAll("\\.", "-") 
                + "." + dateStamp + "." + timeStamp
                + "." + fileType;
        
        System.out.println(fileName);
        
        return fileName;
    }
    
    public static void writeToFile(String locus, String version, String dataType)
    {
        try {
            String text = B12xGUI.neo4jResults.getText();
//            String filePath = fileName(locus, dataType);

            File destinationFile = new File(fileName(locus, version, dataType));

            // if file doesnt exists, then create it
            if (!destinationFile.exists()) {
                destinationFile.createNewFile();
            }

            FileWriter fw = new FileWriter(destinationFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(text);
            bw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    
}
