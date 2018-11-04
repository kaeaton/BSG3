package neo4jRawData;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;

import b12x.LocusNameParser;

public class Neo4jRequest 
{
    
    private String locus, version, request;
    private JsonFactory factory;
//    private StringWriter writer = new StringWriter();
    
    public Neo4jRequest(String incomingLocus, 
                        String currentVersion, 
                        JsonFactory parentFactory)
    {
        locus = incomingLocus;
        factory = parentFactory;
        version = currentVersion;
    }
    
    public String formNeo4jRequest() throws IOException 
    {
        try 
        {
            char quote = '"';
            
            // hla or kir?
            String requestType = LocusNameParser.hlaOrKir(locus);
            
            // hla
            if(requestType.equals("HLA"))
            {
//              request string: MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) WHERE n.locus = "locus" AND r.imgt_release = "version" RETURN n.name, g.name

                request = ("MATCH (n:IMGT_HLA)-[r:HAS_GFE]-(g:GFE) " +
                            "WHERE n.locus = " + quote + locus + quote + " " +
                            "AND r.imgt_release = " + quote + version + quote +
                            " RETURN n.name, g.name");
            }
            
            // kir
            else if(requestType.equals("KIR"))
            {
//          request string MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) WHERE r.imgt_release = "2.7.0" RETURN n.name, g.name
                 
            request = ("MATCH (n:IMGT_KIR)-[r:HAS_GFE]-(g:GFE) " +
                        "WHERE r.imgt_release = " + quote + version + quote +
                        " RETURN n.name, g.name");
            }
            
            // oops
            else
            {
                System.out.println("Problem creating the neo4j data request.");
            }
            
            // generate the json request
            return GenerateJson.jsonGenerator(request, factory);
           
        } catch (Exception ex) 
        {
            System.out.println(ex);
        }
        
        return null;
    }
}
