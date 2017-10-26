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
}
