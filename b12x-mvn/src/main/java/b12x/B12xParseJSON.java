/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

//import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.neo4j.driver.v1.*;
//import org.json.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 *
 * @author KAEaton
 */
public class B12xParseJSON{
    
    public B12xParseJSON() throws IOException {
//        B12xCustomUrl uri = new B12xCustomUrl();
//        URL url = new URL(uri.buildURL());
    }
    
    
    
    public void makeCall(String customURL) throws IOException {
        try {
            
            // Open connection
            URL url = new URL(customURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//            
//            InputStreamReader testInput = new InputStreamReader(connection.getInputStream());
//            // Get data from connection: creates a single string buffer
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
            JsonParser parser = factory.createParser(url);
            

            // continue parsing the token till the end of input is reached
            while (!parser.isClosed()) {
                // get the token
                JsonToken token = parser.nextToken();
                // if its the last token then we are done
                if (token == null)
                    break;
                // we want to look for a field that says dataset

                if (JsonToken.FIELD_NAME.equals(token) && "act_version".equals(parser.getCurrentName())) {
                    // we are entering the datasets now. The first token should be
                    // start of array
                    token = parser.nextToken();
//                    if (!JsonToken.START_ARRAY.equals(token)) {
//                        // bail out
//                        break;
//                    }
                    System.out.println(parser.getText());
                    // each element of the array is an album so the next token
                    // should be 
                    token = parser.nextToken();
                    if (!JsonToken.START_OBJECT.equals(token)) {
                        break;
                    }
                    // we are now looking for a field that says "album_title". We
                    // continue looking till we find all such fields. This is
                    // probably not a best way to parse this json, but this will
                    // suffice for this example.
                    while (true) {
                        token = parser.nextToken();
                        if (token == null)
                                break;
                        if (JsonToken.FIELD_NAME.equals(token) && "term".equals(parser.getCurrentName())) {
                                token = parser.nextToken();
                                System.out.println(parser.getText());
                        }
                    }
                }
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
