/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4jRawData;

/**
 *
 * @author kaeaton
 */
public class Neo4jCurrentVersion {
    
    char quote = '"';
    private final String searchString = "MATCH (n) WHERE " 
            + "EXISTS(n.imgt_release) RETURN DISTINCT " 
            + quote + "node" + quote + "as element, " 
            + "n.imgt_release AS imgt_release " 
            + "UNION ALL MATCH ()-[r]-() WHERE EXISTS(r.imgt_release) "
            + "RETURN DISTINCT " + quote + "relationship" + quote 
            + "AS element, r.imgt_release AS imgt_release";
}
