
package neo4jRawData;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author katrinaeaton
 */
public class Neo4jIncomingData {
    
    public Neo4jIncomingData() {

    }
    
    public void parseResponse(String locus, String version, InputStream httpResult, JsonFactory factory) throws IOException {
        try {
            
            File neo4jRaw = new File(System.getProperty("user.home") 
                    + System.getProperty("file.separator") + "Documents" 
                    + System.getProperty("file.separator") + "BSG"
                    + System.getProperty("file.separator") + "BSGData"
                    + System.getProperty("file.separator") + version
                    + System.getProperty("file.separator") 
                    + "neo4j_" + locus + "_" + version 
                    + "_Download.csv");
            neo4jRaw.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(neo4jRaw));
            
            LocalDate date = LocalDate.now();
            writer.write(date.toString() + System.lineSeparator());
            writer.write(version + System.lineSeparator());

            
            TreeMap<String, String> neo4jPairs = new TreeMap<String, String>();
            
            JsonParser parser = factory.createParser(httpResult);
            
            // continue parsing the token till the end of input is reached
            while (!parser.isClosed()) {
                // get the token
                JsonToken token = parser.nextToken();

                // if its the last token then we are done
                if (token == null)
                    break;
                

                
                // we want to look for a field that says data
                if (JsonToken.FIELD_NAME.equals(token) && "data".equals(parser.getCurrentName())) {
                    // we are entering the datasets now. The first token should be
                    // start of array
                    token = parser.nextToken();

                    // we are now looking for a field that says "row". We
                    // continue looking till we find all such fields. This is
                    // probably not a best way to parse this json, but this will
                    // suffice for this example.
                    while (true) {
                        token = parser.nextToken();
                        if (token == null)
                                break;
                        if (JsonToken.FIELD_NAME.equals(token) && "row".equals(parser.getCurrentName())) {
                            token = parser.nextToken();
                            token = parser.nextToken();
                            String key = parser.getText();
                            System.out.println(parser.getText());
                            token = parser.nextToken();
                            String value = parser.getText();
                            System.out.println(parser.getText());
                            neo4jPairs.put(key, value);
                        }
                    }
                }
            }
            parser.close();
            for(Map.Entry m:neo4jPairs.entrySet()){  
                writer.write(m.getKey() + "," + m.getValue()
                        + System.lineSeparator());  
            }  
            writer.close();
            
            //Debugging tools
            // Write raw data to file to see structure
//            ObjectMapper mapper = new ObjectMapper();
//            Object json = mapper.readValue(httpResult, Object.class);
//            File neo4jRaw = new File(System.getProperty("user.home") 
//                            + System.getProperty("file.separator") 
//                            + "Documents" + System.getProperty("file.separator")
//                            + "neo4jRaw" + locus + "Data.json");
//            mapper.writerWithDefaultPrettyPrinter().writeValue(neo4jRaw, json);  
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public List<String> parseVersion(InputStream httpResult, JsonFactory factory) throws IOException {
        List<String> versions = new ArrayList<>();
        System.out.println(versions.toString());

        try {
            // reading raw data and extracting the version string
            // open the json parser
            JsonParser parser = factory.createParser(httpResult);
            System.out.println("Taken the input string and opened the Version parser");
            
            // continue parsing the token till the end of input is reached
            while (!parser.isClosed()) {
                // get the token
                JsonToken token = parser.nextToken();

                while (true) {
                    token = parser.nextToken();
                    if (token == null)
                            break;
                    
                    // we want to look for a key field that says row
                    if (JsonToken.FIELD_NAME.equals(token) 
                            && "row".equals(parser.getCurrentName())) {
//                    if (JsonToken.VALUE_STRING.equals(token) 
//                            && "row".equals(parser.getText())) {
                        token = parser.nextToken();
                        token = parser.nextToken();
                        versions.add(parser.getText());
                        System.out.println(versions.toString());

                    }
                }
            }
            
            // close the json parser
            parser.close();
            
            
            //Debugging tools
            // Write raw data to file to see structure
//            ObjectMapper mapper = new ObjectMapper();
//            Object json = mapper.readValue(httpResult, Object.class);
//            File neo4jRaw = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "neo4jRawVersionData.json");
//            mapper.writerWithDefaultPrettyPrinter().writeValue(neo4jRaw, json);  
            

            // Write extracted data to file to make sure we're pulling the correct data.
            File neo4jVersionRaw = new File(System.getProperty("user.home") 
                    + System.getProperty("file.separator") + "Documents" 
                    + System.getProperty("file.separator") + "BSG"
                    + System.getProperty("file.separator") + "BSGData"
                    + System.getProperty("file.separator") 
                    + "neo4j_version.txt");
            neo4jVersionRaw.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(neo4jVersionRaw));
            
            LocalDate date = LocalDate.now();
            writer.write(date.toString() + System.lineSeparator());
            for(int i = 0; i < versions.size(); i++){
                writer.write(versions.get(i) + ",");
            }
//            writer.write(versions.toString());
            writer.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return versions;
    }
    
    public List<String> parseKirVersion(InputStream httpResult, JsonFactory factory) throws IOException {
        List<String> versions = new ArrayList<>();
        System.out.println(versions.toString());

        try {
            // reading raw data and extracting the version string
            // open the json parser
            JsonParser parser = factory.createParser(httpResult);
            System.out.println("Taken the input string and opened the Version parser");
            
            // continue parsing the token till the end of input is reached
            while (!parser.isClosed()) {
                // get the token
                JsonToken token = parser.nextToken();

                while (true) {
                    token = parser.nextToken();
                    if (token == null)
                            break;
                    
                    // we want to look for a key field that says row
                    if (JsonToken.FIELD_NAME.equals(token) 
                            && "row".equals(parser.getCurrentName())) { 
                        token = parser.nextToken();
                        token = parser.nextToken();
                        versions.add(parser.getText());
                        System.out.println(versions.toString());

                    }
                }
            }
            
            // close the json parser
            parser.close();
            
            
            //Debugging tools
            // Write raw data to file to see structure
//            ObjectMapper mapper = new ObjectMapper();
//            Object json = mapper.readValue(httpResult, Object.class);
//            File neo4jRaw = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "neo4jRawVersionData.json");
//            mapper.writerWithDefaultPrettyPrinter().writeValue(neo4jRaw, json);  
            

            // Write extracted data to file to make sure we're pulling the correct data.
            File neo4jVersionRaw = new File(System.getProperty("user.home") 
                    + System.getProperty("file.separator") + "Documents" 
                    + System.getProperty("file.separator") + "BSG"
                    + System.getProperty("file.separator") + "BSGData"
                    + System.getProperty("file.separator") 
                    + "neo4j_kir_version.txt");
            neo4jVersionRaw.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(neo4jVersionRaw));
            
            LocalDate date = LocalDate.now();
            writer.write(date.toString() + System.lineSeparator());
            for(int i = 0; i < versions.size(); i++){
                writer.write(versions.get(i) + ",");
            }
//            writer.write(versions.toString());
            writer.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return versions;
    }
}
