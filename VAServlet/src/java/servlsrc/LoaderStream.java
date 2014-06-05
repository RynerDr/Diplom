/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryner
 */
public class LoaderStream implements VideoProvider{


    @Override
    public void load(OutputStream out, String urlStream) {
        
       final int size = 262144;//1048576 много 524288 тормозит 32768 близко 65536 лучше 98304 ещё лучше 
       BufferedInputStream rd = null;
       try {

         URL url = new URL(urlStream); 
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
       
          rd =new BufferedInputStream (conn.getInputStream());
          byte[] buffer = new byte[size];

         while ((rd.read(buffer)) != -1)out.write(buffer); 
              
         }
       catch (IOException e) {
       }  
        finally {
            try {
                if (rd != null) {
                    rd.close();
                }
                if (out != null) {
                    out.close();
                } 
            } catch (IOException ex) {
                Logger.getLogger(LoaderStream.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }   
}
