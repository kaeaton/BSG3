/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j;

import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;

/**
 *
 * @author katrinaeaton
 */
public class Neo4j implements AutoCloseable{
    
    private final Driver driver;
    
    public Neo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }
    
    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void importJSON()
    {
        try ( Session session = driver.session() )
        {
            String incomingJSON = session.readTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    /*
                        MATCH (h:IMGT)-[r1:HAS_GFE]-(g:GFE) 
                        WHERE h.locus = "HLA-A"
                        AND r1.status = "Expected"
                        RETURN h.name, g.name
                    */
                    StatementResult result = tx.run( "MATCH (h:IMGT)-[r1:HAS_GFE]-(g:GFE) " +
                                                     "WHERE h.locus = \"HLA-DRA\" " +
                                                     "AND r1.status = \"Expected\" " +
                                                     "RETURN h.name, g.name" );
//                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( incomingJSON );
        }
    }
    
    public static void main( String... args ) throws Exception
    {
//        try ( Neo4j greeter = new Neo4j( "bolt://localhost:7687", "neo4j", "password" ) )
        try ( Neo4j greeter = new Neo4j( "bolt+routing://neo4j.b12x.org", "keaton", "chori17" ) )
        {
            greeter.importJSON();
        }
    }
}
