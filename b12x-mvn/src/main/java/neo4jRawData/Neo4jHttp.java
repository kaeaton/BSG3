/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4jRawData;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
//import java.net.URL;
import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author katrinaeaton
 */
public class Neo4jHttp {
    
    private InputStream incomingData;

    public Neo4jHttp() throws IOException {

    }
       
    public InputStream makeCall(URL customURL, String params) throws IOException {
        try {
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) customURL.openConnection();
            
            // Setup the connection
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Stream", "true");
            connection.setRequestProperty("Authorization", "Basic a2VhdG9uOmNob3JpMTc=");
            
            // Send our request
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(params);
            wr.flush();
            
            // Did we get a response?
            int httpResult = connection.getResponseCode(); 
            if (httpResult == HttpURLConnection.HTTP_OK) {
                incomingData = connection.getInputStream();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return incomingData;
    }
    
}