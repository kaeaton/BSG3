/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import b12xAPI.*;
import java.io.IOException;
import javax.swing.SwingWorker;
import neo4j.Neo4jHttp;


/**
 *
 * @author katrinaeaton
 */
public class B12x extends SwingWorker<Void, String> {
    
    public String b12xUri;
    public String neo4jUri;
    public String locus;
    
    public B12x() {
//        uri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
    }
    
    @Override
    protected Void doInBackground() throws IOException {
        
        try {
            b12xUri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
            System.out.println(b12xUri);
            
            B12xAPICustomUrl uri = new B12xAPICustomUrl();
            B12xParseJSON parser = new B12xParseJSON();
            parser.makeCall(uri.buildURL());
            
            neo4jUri = "http://neo4j.b12x.org/db/data/transaction/commit";
            locus = "HLA-A";

            Neo4jHttp neo4j = new Neo4jHttp();
            neo4j.makeCall(neo4jUri, locus);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    @Override
    protected void done() {System.out.println("B12x complete.");}

}
