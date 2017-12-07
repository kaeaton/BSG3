
package neo4j;

import neo4jRawData.Neo4jHttp;
import neo4jRawData.Neo4jRequest;
import neo4jRawData.Neo4jIncomingData;
import neo4jRawData.Neo4jDateCheck;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import javax.swing.SwingWorker;


public class Neo4j  extends SwingWorker<String, String> {
    
    private final String locus;
    private final Path path;
    private final URL neo4jURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");
    private final String regex;
    private JsonFactory factory;

//    private String request;
    
    public Neo4j(String incomingLocus, Path incomingPath, String incomingRegex) throws IOException {
        locus = incomingLocus;
        path = incomingPath;
        regex = incomingRegex;
//        path = Paths.get(System.getProperty("user.home") 
//                    + System.getProperty("file.separator") + "Documents" 
//                    + System.getProperty("file.separator") 
//                    + "neo4j_" + locus + "_Download.csv");
        factory = new JsonFactory();
    }
        
    
    public void dataUpdate() throws IOException {
        try {
            Neo4jRequest request = new Neo4jRequest(locus, factory);
            Neo4jHttp neo4jHttp = new Neo4jHttp();
            InputStream incomingData = neo4jHttp.makeCall(neo4jURL, request.formNeo4jRequest());
            Neo4jIncomingData parser = new Neo4jIncomingData();
            parser.parseResponse(locus, incomingData, factory);
            
            // Check to see if this is working
            Neo4jDateCheck dataCheck = new Neo4jDateCheck();
            dataCheck.getFileDate(path.toFile());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    protected String doInBackground() throws IOException {
//    public void fetchData() throws IOException {
        try {
//            Neo4jRequest request = new Neo4jRequest(locus, factory);
//            Neo4jHttp neo4jHttp = new Neo4jHttp();
//            InputStream incomingData = neo4jHttp.makeCall(neo4jURL, request.formNeo4jRequest());
            Neo4jDateCheck dataCheck = new Neo4jDateCheck();
            if (dataCheck.checkDate(path.toFile()) != true){
                dataUpdate();
//                Neo4jIncomingData parser = new Neo4jIncomingData();
//                parser.parseResponse(locus, incomingData, factory);
            }
            Neo4jDataIO.readCSVFile(locus, path.toFile(), regex);
//            Neo4jLocusA hlaA = new Neo4jLocusA();
//            hlaA.parseLocus(locus);
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
}
