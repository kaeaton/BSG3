/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b12x;

import java.io.IOException;
import java.net.URL;

/**
 *
 * @author KAEaton
 */
public class B12xActObject {
    
    private String actVersion;
    private String features;
    
    
    public B12xActObject() throws IOException {
        try {
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public String get(String input){
        return input;
    }
}
