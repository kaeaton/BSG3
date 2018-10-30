package neo4jRawData;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonEncoding;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Neo4jKirVersionRequest {
    
    private String locus, version, request;
    private JsonFactory factory;
    private StringWriter writer = new StringWriter();
    
    public Neo4jKirVersionRequest(String incomingLocus, 
                        String currentVersion, 
                        JsonFactory parentFactory)
    {
        locus = incomingLocus;
        factory = parentFactory;
        version = currentVersion;
    }
    
    public String formNeo4jKirRequest() throws IOException {
        try {
            char quote = '"';
            
//          request string: MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS KIR_DB
                 
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
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return writer.toString();
    }
    
}
