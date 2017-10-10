/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
//import javax.json.stream.JsonParser;
import org.json.*;
import org.json.JSONObject;
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
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(connection);

              // Try via buffered reader. Trouble capturing the input stream.
//            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//            String inputLine;
//            JSONObject obj = new JSONObject(in.readLine()); 

//            while ((inputLine = in.readLine()) != null)
//                System.out.println(inputLine);
//                JSONObject obj = new JSONObject().put("", inputLine); 

//            in.close();
//            System.out.println(obj);

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
//            InputStream json = connection.getInputStream();
//                       InputStreamReader json2 = new InputStreamReader(json);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();

            //print result
//            System.out.println(response.toString());
//            Scanner json2 = new Scanner(json);
//            String json3 = json2.next();
//            json3 = json2.next();
//                System.out.println(json3);

//            json3 = json2.next();
//                System.out.println(json3);

//            Object json = connection.Scanner();
//            JSONObject json = new JSONObject(url.getContent());

//            json.toString();
//            System.out.println(json2);
//            System.out.println(json3);
//            while (json2 != null)
//                json3.append(json2.next());
//                System.out.println(json3);
//            JsonParser parser = Json.createParser(new FileReader(json));
//             token = new JSONTokener(json);
//            System.out.println(token);
            JSONObject obj = new JSONObject(response.toString());

            System.out.println(obj);

            String n = obj.getString("act_version");
            System.out.println(n);
//
            connection.disconnect();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
//    public static void main(String[] args) {
//        // TODO code application logic here
//
//    }
}
