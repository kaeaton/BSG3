/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12xAPI;

import java.io.IOException;

/**
 *
 * @author KAEaton
 */
public class B12xAPICustomUrl {
    
    public String uri;
    
    public B12xAPICustomUrl() {
//        uri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
    }
    
    public String buildURL() throws IOException {
        
        try {
            uri = "http://act.b12x.org/act?locus=HLA-A&gfe=HLA-Aw1-1-7-20-10-32-7-1-1-1-6-1-5-3-5-1-1";
            System.out.println(uri);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return uri;
    }
    
    
}
