/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4jRawData;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

/**
 *
 * @author kaeaton
 */
public class Neo4jVersionRequest 
{
    
    private String request;
//    private JsonFactory factory;
    
    public Neo4jVersionRequest() //JsonFactory parentFactory)
    {
//        factory = parentFactory;
    }
    
    public String formNeo4jVersionRequest(String versionType, JsonFactory factory) throws IOException 
    {
        try 
        {
            // Is it an HLA or KIR request?
            // hla
            if(versionType.equals("HLA"))
            {
                request = "MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) " 
                        + "RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY "
                        + "e.imgt_release DESC";
//              request string: MATCH (n:IMGT_HLA)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS HLA_DB ORDER BY r.imgt_release DESC
            } 
            
            // kir
            else if(versionType.equals("KIR"))
            {
                request = "MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) " 
                        + "RETURN DISTINCT e.imgt_release AS KIR_DB " 
                        + "ORDER BY e.imgt_release DESC";
//              request string: MATCH (n:IMGT_KIR)-[e:HAS_FEATURE]-(feat:FEATURE) RETURN DISTINCT e.imgt_release AS KIR_DB ORDER BY e.imgt_release DESC
            } else
                
            // oops
            {
                System.out.println("versionType neither HLA nor KIR");
            }
            
            // generate json request
            return GenerateJson.jsonGenerator(request, factory);

        } catch (Exception ex) 
        {
            System.out.println(ex);
        }
        return null;
    }
}
