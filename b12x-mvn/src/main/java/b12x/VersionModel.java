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
import variables.GlobalVariables;

/**
 *
 * @author katrinaeaton
 */
public class VersionModel {
    
//    Global variables = new Global();
    
    public VersionModel() {
        
    }
    
    static String[] getVersionData(String versionType) throws IOException {
        Path versionPath = Paths.get(GlobalVariables.dataFilesPath() 
                                     + "neo4j_" + versionType + "_version.txt");
        File file = versionPath.toFile();
        if (file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            // date stamp
            String line = br.readLine();
            
            // versions
            line = br.readLine();
            
            String[] versions = line.split(",");    
            System.out.println("Versions array: " + Arrays.toString(versions));
            br.close();
            return versions;
        } else {
            return null;
        }
    }
    
    public static List<String> downloadVersionData(String versionType) throws IOException {
        List<String> versions;
        // set up the call
        Neo4jHttp neo4jHttp = new Neo4jHttp();

        // set up for parsing the incoming data
        Neo4jIncomingData parser = new Neo4jIncomingData();

        // create the request and send it
        JsonFactory factory = GlobalVariables.factory();
        URL neo4jURL = new URL(GlobalVariables.neo4jUrl());
        
        Neo4jVersionRequest whatVersion = new Neo4jVersionRequest(factory);

        // what kind of version data are we looking for?
        if (versionType == "HLA")
        {
            InputStream incomingVersionData = neo4jHttp
                .makeCall(neo4jURL, whatVersion.formNeo4jVersionRequest());

            // recieve the version data and parse it
            versions = parser.parseVersion(incomingVersionData, factory, versionType);
                    
            return versions;

        }
        else if (versionType == "KIR")
        {
            InputStream incomingVersionData = neo4jHttp
                .makeCall(neo4jURL, whatVersion.formNeo4jKirVersionRequest());

            // recieve the version data and parse it
            versions = parser.parseVersion(incomingVersionData, factory, versionType);

            System.out.println("versionType is neither HLA nor KIR");
            return versions;
        }

        // recieve the version data and parse it
//        versions = parser.parseVersion(incomingVersionData, factory);
        
        return null;
    }
    
    public static DefaultComboBoxModel versions(String versionType) throws IOException {

        List<String> versions;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        String[] versionData = getVersionData(versionType);
        
        // is there a data file to read from?
        if (versionData != null)
            model = new DefaultComboBoxModel(versionData);
        
        // no? create one
        else {
            
            System.out.println("Realized there's no version data file.");
            
            versions = downloadVersionData(versionType);
            model = new DefaultComboBoxModel(versions.toArray());

        }
        
        return model;
    }
    
//    public static DefaultComboBoxModel kirVersions() throws IOException {
//
//        List<String> versions;
//        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
//        String[] kirVersionData = getVersionData();
        
        // is there a data file to read from?
//        if (kirVersionData != null)
//            model = new DefaultComboBoxModel(kirVersionData);
        
        // no? create one
//        else {
//            
//            System.out.println("Realized there's no version data file.");
//            
////            versions = downloadVersionData(versionType);
////            model = new DefaultComboBoxModel(versions.toArray());
//
//        }
        
//        return model;
//    }
    
}
