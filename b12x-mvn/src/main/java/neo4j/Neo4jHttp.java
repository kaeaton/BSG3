/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
//import java.net.URL;
import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author katrinaeaton
 */
public class Neo4jHttp {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KAEaton
 */
    public Neo4jHttp() throws IOException {
//        B12xCustomUrl uri = new B12xCustomUrl();
//        URL url = new URL(uri.buildURL());
    }
    
    
    
    public void makeCall(String customURL, String locus) throws IOException {
        try {
            
            // Open connection
            URL url = new URL(customURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Stream", "true");
            connection.setRequestProperty("Authorization", "Basic a2VhdG9uOmNob3JpMTc=");
            
            Neo4jRequest customParams = new Neo4jRequest(locus);
            String params = customParams.formNeo4jRequest();


            
//            InputStreamReader testInput = new InputStreamReader(connection.getInputStream());
//            DataInputStream dataIn = new DataInputStream(connection.getInputStream());
            
//            DataInputStream dataIn = 
            
            // Get data from connection: creates a single string buffer
//            BufferedReader in = new BufferedReader(
//                new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close(); 

//            System.out.println(response.toString());

            // get an instance of the json parser from the json factory
            JsonFactory factory = new JsonFactory();  
            JsonParser parser = factory.createParser(connection.getInputStream());
            

            // continue parsing the token till the end of input is reached
            while (!parser.isClosed()) {
                // get the token
                JsonToken token = parser.nextToken();
                // if its the last token then we are done
                if (token == null)
                    break;
                // we want to look for a field that says dataset

//                if (JsonToken.FIELD_NAME.equals(token) && "act_version".equals(parser.getCurrentName())) {
                    // we are entering the datasets now. The first token should be
                    // start of array
//                    token = parser.nextToken();
//                    if (!JsonToken.START_ARRAY.equals(token)) {
//                        // bail out
//                        break;
//                    }
                else{
                    System.out.println(parser.getText());
                }
                    // each element of the array is an album so the next token
                    // should be 
//                    token = parser.nextToken();
//                    if (!JsonToken.START_OBJECT.equals(token)) {
//                        break;
//                    }
                    // we are now looking for a field that says "album_title". We
                    // continue looking till we find all such fields. This is
                    // probably not a best way to parse this json, but this will
                    // suffice for this example.
//                    while (true) {
//                        token = parser.nextToken();
//                        if (token == null)
//                                break;
//                        if (JsonToken.FIELD_NAME.equals(token) && "term".equals(parser.getCurrentName())) {
//                                token = parser.nextToken();
//                                System.out.println(parser.getText());
//                        }
//                    }
//                }
            }
            
            // Org.JSON code - creating garbled JSON file 
//            JSONObject obj = new JSONObject(response.toString());
//            System.out.println(obj);
            
            // Search testing
//            String m = p.getCurrentName();
//            // "act_version"
//            String n = p.getValueAsString("act_version");
//            
//            p.close();
//
//            System.out.println(m);
//            System.out.println(n);

//            connection.disconnect();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
//    public static void main(String[] args) {
//        // TODO code application logic here
//
//    }
}