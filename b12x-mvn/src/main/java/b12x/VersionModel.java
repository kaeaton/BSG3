/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import neo4jRawData.Neo4jHttp;
import neo4jRawData.Neo4jIncomingData;
import neo4jRawData.Neo4jVersionRequest;

/**
 *
 * @author katrinaeaton
 */
public class VersionModel {
    
    public VersionModel() {
        
    }
    
    static String[] getVersionData() throws IOException {
        Path path = Paths.get(System.getProperty("user.home") 
                + System.getProperty("file.separator") + "Documents" 
                + System.getProperty("file.separator") + "BSG"
                + System.getProperty("file.separator") + "BSGData"
                + System.getProperty("file.separator") 
                + "neo4j_version.txt");
        File file = path.toFile();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        line = br.readLine();
        String[] versions = line.split(",");    
        System.out.println("Last updated: " + Arrays.toString(versions));
        br.close();
        return versions;
//        } catch (Exception ex) {
//            System.out.println(ex); 
//        }
//        return fileData;
    }
    
    static DefaultComboBoxModel versions() throws IOException {
        String labels[] = { "A", "B", "C", "D", "E" };

//        try {
        
//        // set up the call
//        Neo4jHttp neo4jHttp = new Neo4jHttp();
//            
//        // set up for parsing the incoming data
//        Neo4jIncomingData parser = new Neo4jIncomingData();
//
//        // determine the most recent version
//        // create the request and send it
//        JsonFactory factory = new JsonFactory();
//        URL neo4jURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");
//
//        Neo4jVersionRequest whatVersion = new Neo4jVersionRequest(factory);
//        InputStream incomingVersionData = neo4jHttp
//                .makeCall(neo4jURL, whatVersion.formNeo4jVersionRequest());

        // recieve the version data and parse it
//        List<String> versions;
//        versions = parser.parseVersion(incomingVersionData, factory);
        
//        getVersionData();
        DefaultComboBoxModel model = new DefaultComboBoxModel(getVersionData());

//        DefaultComboBoxModel model = new DefaultComboBoxModel(versions.toArray());
//        DefaultComboBoxModel model = new DefaultComboBoxModel(labels);

//        System.out.println(versions.toString());
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
        return model;
    }
    
}
