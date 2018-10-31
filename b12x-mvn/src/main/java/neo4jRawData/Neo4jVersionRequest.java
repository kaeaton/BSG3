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
    
    public String formNeo4jVersionRequest() throws IOException 
    {
        try 
        {
            char quote = '"';
            
            request = "MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) " 
                    + "RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY "
                    + "e.imgt_release DESC";
//              request = "MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY r.imgt_release DESC";

//             MATCH (n) WHERE EXISTS(n.imgt_release) 
//             RETURN DISTINCT "node" as element, n.imgt_release 
//             AS imgt_release UNION ALL MATCH ()-[r]-() 
//             WHERE EXISTS(r.imgt_release) RETURN DISTINCT "relationship" 
//             AS element, r.imgt_release AS imgt_release
//             ORDER BY r.imgt_release DESC 

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
        } catch (Exception ex) 
        {
            System.out.println(ex);
        }
        return writer.toString();
    }
    
    public String formNeo4jKirRequest() throws IOException 
    {
        try 
        {
            char quote = '"';
            
//          request string: MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS KIR_DB ORDER BY e.imgt_release DESC
                 
            request = "MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) " +
                      "RETURN DISTINCT e.imgt_release AS KIR_DB " +
                      "ORDER BY e.imgt_release DESC";
            
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
        } catch (Exception ex) 
        {
            System.out.println(ex);
        }
        return writer.toString();
    }
}
