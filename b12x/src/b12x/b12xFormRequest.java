/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
//import javax.json.stream.JsonParser;
import org.json.*;
/**
 *
 * @author KAEaton
 */
public class b12xFormRequest{
    String uri;
    
    public b12xFormRequest() throws IOException {
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
            URL url = new URL(customURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            JSONObject obj = new JSONObject(in); 

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
            System.out.println(obj);
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//            InputStream json = connection.getInputStream();
//            Object json = connection.getContent();

//            json.toString();
//            System.out.println(json);
////            JsonParser parser = Json.createParser(new FileReader(json));
//            JSONObject obj = new JSONObject(json);
            String n = obj.getString("act_version");
//            System.out.println(obj);
            System.out.println(n);
//            System.out.println("Test");
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
