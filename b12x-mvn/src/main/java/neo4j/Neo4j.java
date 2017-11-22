
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


public class Neo4j {
    
    private final String locus;
    private Path path;
    private final URL neo4jURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");

//    private String request;
    
    public Neo4j(String incomingLocus, Path incomingPath) throws IOException {
        locus = incomingLocus;
        path = Paths.get(System.getProperty("user.home") 
                    + System.getProperty("file.separator") + "Documents" 
                    + System.getProperty("file.separator") 
                    + "neo4j_" + locus + "_Download.csv");
    }
        
    public void fetchData() throws IOException {
        try {
            JsonFactory factory = new JsonFactory();
            Neo4jRequest request = new Neo4jRequest(locus, factory);
            Neo4jHttp neo4jHttp = new Neo4jHttp();
            InputStream incomingData = neo4jHttp.makeCall(neo4jURL, request.formNeo4jRequest());
            Neo4jDateCheck dataCheck = new Neo4jDateCheck();
            if (dataCheck.checkDate(path.toFile()) != true){
                Neo4jIncomingData parser = new Neo4jIncomingData();
                parser.parseResponse(locus, incomingData, factory);
            }
//            Neo4jDataIO neo4jIO = new Neo4jDataIO();
//            neo4jIO.readCSVFile(path.toFile());
            Neo4jLocusA hlaA = new Neo4jLocusA();
            hlaA.parseLocusA(locus);
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        return null;
    }
}
