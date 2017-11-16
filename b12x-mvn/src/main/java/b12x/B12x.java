/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import b12xAPI.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.SwingWorker;
import neo4j.Neo4j;


/**
 *
 * @author katrinaeaton
 */
public class B12x extends SwingWorker<Void, String> {

    private String locus;
    private String b12xUri;
    private final String neo4jUri = new String("http://neo4j.b12x.org/db/data/transaction/commit");

    
    public B12x() {
//        uri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
    }
    
    @Override
    protected Void doInBackground() throws IOException {
        
        try {
//            b12xUri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
//            System.out.println(b12xUri);
//            
//            B12xAPICustomUrl uri = new B12xAPICustomUrl();
//            B12xParseJSON parser = new B12xParseJSON();
//            parser.makeCall(uri.buildURL());
            
            locus = "HLA-A";

            Neo4j neo4j = new Neo4j(locus);
            neo4j.fetchData();
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    @Override
    protected void done() {System.out.println("B12x complete.");}

}