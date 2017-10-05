/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBContext;
//import org.json.*;
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

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            System.out.println("Test");

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
