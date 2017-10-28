/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12xAPI;

import java.io.IOException;
import javax.swing.SwingWorker;
import neo4j.Neo4j;


/**
 *
 * @author KAEaton
 */
public class B12xAPI  extends SwingWorker<Void, String>{
    
    public String uri;
    
    public B12xAPI() {
//        uri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
    }
    
    
    @Override
    protected Void doInBackground() throws IOException {
        
        try {
            uri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
            System.out.println(uri);
            
            B12xAPICustomUrl uri = new B12xAPICustomUrl();
            B12xParseJSON parser = new B12xParseJSON();
            parser.makeCall(uri.buildURL());
            Neo4j neo4j = new Neo4j("bolt://neo4j.b12x.org/db/data/index/node", "keaton", "chori17");
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    @Override
    protected void done() {System.out.println("B12x complete.");}

    
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    
}
