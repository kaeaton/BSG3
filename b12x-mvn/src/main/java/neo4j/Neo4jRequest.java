/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

/**
 *
 * @author katrinaeaton
 */
public class Neo4jRequest {
    
    private String locus;
    private String request;
    
    public Neo4jRequest(String incomingLocus) {
        locus = incomingLocus;
    }
    
    public String formNeo4jRequest(){
        request = ( "MATCH (h:IMGT)-[r1:HAS_GFE]-(g:GFE) " +
                    "WHERE h.locus = \"" + locus + "\" " +
                    "AND r1.status = \"Expected\" " +
                    "RETURN h.name, g.name" );
        
        return request;
    }
    
}
