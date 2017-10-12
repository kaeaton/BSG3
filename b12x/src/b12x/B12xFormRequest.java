/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import com.fasterxml.jackson.core.*;
import java.io.BufferedReader;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

/**
 *
 * @author KAEaton
 */
public class B12xFormRequest{
    private String uri;
    
    public B12xFormRequest() throws IOException {
        URL url = new URL(buildURL());
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "application/json");
//        System.out.println("Test");
//        
//        connection.disconnect();
    }
    
    public String buildURL(){
        uri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
        System.out.println(uri);
        return uri;
    }
    
    public void makeCall(String customURL) throws IOException {
        try {
            
            // Open connection
            URL url = new URL(customURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            
            // Get data from connection: creates a single string buffer
            BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close(); 

//            System.out.println(response.toString());
            

            
            // Org.JSON code - creating garbled JSON file 
//            JSONObject obj = new JSONObject(response.toString());
//            System.out.println(obj);

            
            // Search testing
//            String n = obj.getString("act_version");
//            String n = obj.getString("rank");

//            System.out.println(n);
//
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
