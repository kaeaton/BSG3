/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;


/**
 *
 * @author katrinaeaton
 */
public class Neo4jRequest {
    
    private String locus;
    private String request;
    private OutputStream outGoingRequest;
    
    public Neo4jRequest(String incomingLocus) {
        locus = incomingLocus;
    }
    
    public OutputStream formNeo4jRequest() throws IOException {
        try {
            request = ("MATCH (h:IMGT)-[r1:HAS_GFE]-(g:GFE) " +
                        "WHERE h.locus = \"" + locus + "\" " +
                        "AND r1.status = \"Expected\" " +
                        "RETURN h.name, g.name");

            JsonFactory factory = new JsonFactory();
            JsonGenerator generator = factory.createGenerator(outGoingRequest);

            // start writing with {
            generator.writeStartObject();
            generator.writeFieldName("statements");
    //		generator.writeString("Free Music Archive - Albums");
    //		generator.writeFieldName("dataset");
            // start an array
            generator.writeStartArray();
            generator.writeStartObject();
            generator.writeStringField("statement", request);
            generator.writeEndObject();
            generator.writeEndArray();
            generator.writeEndObject();

            String jsonValue = outGoingRequest.toString();
            System.out.println(jsonValue);            
            
            generator.close();
            
//            BufferedReader in = new BufferedReader(
//                new InputStreamReader(outGoingRequest.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
////            in.close(); 
//
//            System.out.println(response.toString());

            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return outGoingRequest;
    }
    
}
