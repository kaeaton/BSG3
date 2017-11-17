
package neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Neo4j {
    
    private final String locus;
    private final URL neo4jURL = new URL("http://neo4j.b12x.org/db/data/transaction/commit");

//    private String request;
    
    public Neo4j(String incomingLocus) throws IOException {
        locus = incomingLocus;
    }
    
    public void fetchData() throws IOException {
        try {
            JsonFactory factory = new JsonFactory();
            Neo4jRequest request = new Neo4jRequest(locus, factory);
            Neo4jHttp neo4jHttp = new Neo4jHttp();
            InputStream incomingData = neo4jHttp.makeCall(neo4jURL, request.formNeo4jRequest());
            Neo4jParser parser = new Neo4jParser();
            parser.parseResponse(locus, incomingData, factory);
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        return null;
    }
}
