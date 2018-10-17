/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4jRawData;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.io.StringWriter;

/**
 *
 * @author kaeaton
 */
public class Neo4jVersionRequest {
    
    private String request;
    private JsonFactory factory;
    private StringWriter writer = new StringWriter();
    
    public Neo4jVersionRequest(JsonFactory parentFactory)
    {
        factory = parentFactory;
    }
    
    public String formNeo4jVersionRequest() throws IOException {
        try {
            char quote = '"';
            request = "MATCH ()-[r]-() WHERE EXISTS(r.imgt_release) "
                    + "RETURN DISTINCT " + quote + "relationship" + quote 
                    + "AS element, r.imgt_release AS imgt_release "
                    + "ORDER BY r.imgt_release DESC ";
            
//            request = "MATCH (n) WHERE " 
//                    + "EXISTS(n.imgt_release) RETURN DISTINCT " 
//                    + quote + "node" + quote + "as element, " 
//                    + "n.imgt_release AS imgt_release " 
//                    + "UNION ALL MATCH ()-[r]-() WHERE EXISTS(r.imgt_release) "
//                    + "RETURN DISTINCT " + quote + "relationship" + quote 
//                    + "AS element, r.imgt_release AS imgt_release "
//                    + "ORDER BY r.imgt_release DESC ";
            
            // look for new releases, would be let know if new relationships 
            
//             MATCH (n) WHERE EXISTS(n.imgt_release) 
//             RETURN DISTINCT "node" as element, n.imgt_release 
//             AS imgt_release UNION ALL MATCH ()-[r]-() 
//             WHERE EXISTS(r.imgt_release) RETURN DISTINCT "relationship" 
//             AS element, r.imgt_release AS imgt_release
//             ORDER BY r.imgt_release DESC 

//          Set descending, limit 2
//          MATCH (n) WHERE EXISTS(n.imgt_release) RETURN DISTINCT "node" as element, n.imgt_release AS imgt_release LIMIT 2 UNION ALL MATCH ()-[r]-() WHERE EXISTS(r.imgt_release) RETURN DISTINCT "relationship" AS element, r.imgt_release AS imgt_release ORDER BY r.imgt_release DESC LIMIT 2
//          Set descending, limit 1
//          MATCH (n) WHERE EXISTS(n.imgt_release) RETURN DISTINCT "node" as element, n.imgt_release AS imgt_release LIMIT 1 UNION ALL MATCH ()-[r]-() WHERE EXISTS(r.imgt_release) RETURN DISTINCT "relationship" AS element, r.imgt_release AS imgt_release ORDER BY r.imgt_release DESC LIMIT 1
//          Set descending, all
//          MATCH (n) WHERE EXISTS(n.imgt_release) RETURN DISTINCT "node" as element, n.imgt_release AS imgt_release UNION ALL MATCH ()-[r]-() WHERE EXISTS(r.imgt_release) RETURN DISTINCT "relationship" AS element, r.imgt_release AS imgt_release ORDER BY r.imgt_release DESC


            JsonGenerator generator = factory.createGenerator(writer);

            // start writing with {
            generator.writeStartObject();
            generator.writeFieldName("statements");
            generator.writeStartArray();
            generator.writeStartObject();
            generator.writeStringField("statement", request);
            generator.writeEndObject();
            generator.writeEndArray();
            generator.writeEndObject();
            generator.close();

            System.out.println(writer.toString());            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return writer.toString();
    }
}
