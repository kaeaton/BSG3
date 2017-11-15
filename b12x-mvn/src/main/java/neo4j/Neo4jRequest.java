package neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonEncoding;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Neo4jRequest {
    
    private String locus;
    private JsonFactory factory;
    private String request;
    private StringWriter writer = new StringWriter();
    
    public Neo4jRequest(String incomingLocus, JsonFactory parentFactory){
        locus = incomingLocus;
        factory = parentFactory;
    }
    
    public String formNeo4jRequest() throws IOException {
        try {
            request = ("MATCH (h:IMGT)-[r1:HAS_GFE]-(g:GFE) " +
                        "WHERE h.locus = \"" + locus + "\" " +
                        "AND r1.status = \"Expected\" " +
                        "RETURN h.name, g.name");
            
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
