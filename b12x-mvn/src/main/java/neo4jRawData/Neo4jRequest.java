package neo4jRawData;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonEncoding;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Neo4jRequest {
    
    private String locus, version, request;
    private JsonFactory factory;
    private StringWriter writer = new StringWriter();
    
    public Neo4jRequest(String incomingLocus, 
                        String currentVersion, 
                        JsonFactory parentFactory)
    {
        locus = incomingLocus;
        factory = parentFactory;
        version = currentVersion;
    }
    
    public String formNeo4jRequest() throws IOException {
        try {
            char quote = '"';
            
//          request string: MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) WHERE n.locus = "locus" AND r.imgt_release = "version" RETURN n.name, g.name
                 
            request = ("MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) " +
                        "WHERE n.locus = " + quote + locus + quote + " " +
                        "AND r.imgt_release = " + quote + version + quote +
                        " RETURN n.name, g.name");
            
//            Old request
//            request = ("MATCH (h:IMGT)-[r1:HAS_GFE]-(g:GFE) " +
//                        "WHERE h.locus = \"" + locus + "\" " +
//                        "AND r1.status = \"Expected\" " +
//                        "RETURN h.name, g.name");
            
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
    
    public String formNeo4jKirRequest() throws IOException {
        try {
            char quote = '"';
            
            // request string MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) WHERE r.imgt_release = "2.7.0" RETURN n.name, g.name
                 
            request = ("MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) " +
                        "WHERE r.imgt_release = " + quote + version + quote +
                        " RETURN n.name, g.name");
            
//            Old request
//            request = ("MATCH (h:IMGT)-[r1:HAS_GFE]-(g:GFE) " +
//                        "WHERE h.locus = \"" + locus + "\" " +
//                        "AND r1.status = \"Expected\" " +
//                        "RETURN h.name, g.name");
            
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
