
package neo4j;

import java.io.IOException;
import java.net.URL;

public class Neo4j {
    
    private final String locus;
    private final URL neo4jURL;

//    private String request;
    
    public Neo4j(String incomingLocus, String incomingURL) throws IOException {
        locus = incomingLocus;
        neo4jURL = new URL(incomingURL);
    }
    
    public void fetchData() throws IOException {
        try {
            Neo4jRequest request = new Neo4jRequest(locus);
            Neo4jHttp neo4jHttp = new Neo4jHttp();
            neo4jHttp.makeCall(neo4jURL, request.formNeo4jRequest());
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        return null;
    }
}
