
package neo4j;

import neo4jRawData.*;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingWorker;


public class Neo4j  extends SwingWorker<String, String> {
    
    private final String locus, regex, searchString;
    private final String version;
    private List<String> versions;
    private final Path path;
    private final URL neo4jURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");
    private JsonFactory factory;

//    private String request;
    
    public Neo4j(String incomingLocus, 
                 String incomingVersion, 
                 Path incomingPath, 
                 String incomingRegex, 
                 String incomingSearchString) 
                 throws IOException 
    {
        locus = incomingLocus;
        path = incomingPath;
        regex = incomingRegex;
        searchString = incomingSearchString;
        version = incomingVersion; //"3.31.0";
//        path = Paths.get(System.getProperty("user.home") 
//                    + System.getProperty("file.separator") + "Documents" 
//                    + System.getProperty("file.separator") 
//                    + "neo4j_" + locus + "_Download.csv");
        factory = new JsonFactory();
    }
        
    
    public void dataUpdate() throws IOException {
        try {
            // set up the call
            Neo4jHttp neo4jHttp = new Neo4jHttp();
            
            // set up for parsing the incoming data
            Neo4jIncomingData parser = new Neo4jIncomingData();
            
            // determine the most recent version
            // create the request and send it
//            Neo4jVersionRequest whatVersion = new Neo4jVersionRequest(factory);
//            InputStream incomingVersionData = neo4jHttp
//                    .makeCall(neo4jURL, whatVersion.formNeo4jVersionRequest());
//            
//            // recieve the version data and parse it
//            versions = parser.parseVersion(incomingVersionData, factory);
//            System.out.println(versions.toString());

            // retrieve the data
            // create the request and send it
            Neo4jRequest request = new Neo4jRequest(locus, version, factory);
            InputStream incomingData = neo4jHttp.makeCall(neo4jURL, request.formNeo4jRequest());
            
            // recieve data and parse it
            parser.parseResponse(locus, version, incomingData, factory);
            
            // Check to see if this is working
            Neo4jFileCheck dataCheck = new Neo4jFileCheck();
            dataCheck.getFileDate(path.toFile());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
//    public DefaultComboBoxModel versions() throws IOException {
//        String labels[] = { "A", "B", "C", "D", "E" };
//        DefaultComboBoxModel model = new DefaultComboBoxModel(labels);
//
//        try {
//        
//        // set up the call
//        Neo4jHttp neo4jHttp = new Neo4jHttp();
//            
//        // set up for parsing the incoming data
//        Neo4jIncomingData parser = new Neo4jIncomingData();
//
//        // determine the most recent version
//        // create the request and send it
//        Neo4jVersionRequest whatVersion = new Neo4jVersionRequest(factory);
//        InputStream incomingVersionData = neo4jHttp
//                .makeCall(neo4jURL, whatVersion.formNeo4jVersionRequest());
//
//        // recieve the version data and parse it
//        versions = parser.parseVersion(incomingVersionData, factory);
//        System.out.println(versions.toString());
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        return model;
//    }
    
    @Override
    protected String doInBackground() throws IOException {
        try {
            
            // do the directories exist? if not create them and get data
            if (!path.toFile().exists())
            {
                System.out.println("The file does not exist.");
                path.toFile().getParentFile().mkdirs();
                path.toFile().createNewFile();
                dataUpdate();
            }
            
            // if the data exists, check the date on it
            // if older than 30 days old, redownload it.
            Neo4jFileCheck dataCheck = new Neo4jFileCheck();
            if (dataCheck.checkDate(path.toFile()) != true)
            {
                dataUpdate();
            }
            Neo4jDataIO parseData = new Neo4jDataIO();
            parseData.readCSVFile(locus, path.toFile(), regex, searchString);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
}
