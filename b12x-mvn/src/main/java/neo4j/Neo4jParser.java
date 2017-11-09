
package neo4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 *
 * @author katrinaeaton
 */
public class Neo4jParser {
    
    int httpResult;
    
    public Neo4jParser() {

    }
    
    public void parseResponse(InputStream httpResult) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(httpResult, Object.class);
        
//        Object json = mapper.readValue(input, Object.class);
//        String indented = (new JSONObject(httpResult)).toString(4);
//        StringBuilder sb = new StringBuilder();  
////        int httpResult = connection.getResponseCode(); 
//        if (httpResult == HttpURLConnection.HTTP_OK) {
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream(), "utf-8"));
//            String line = null;  
//            while ((line = br.readLine()) != null) {  
//                sb.append(line + "\n");  
//            }
//        br.close();
//        System.out.println("" + sb.toString());
    }
}
