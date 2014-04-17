/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import java.io.OutputStream;

/**
 *
 * @author Ryner
 */
public interface VideoProvider {
   /*public String Load(String reqtypefile, int reqid){
       return null;
   }*/
    void load(OutputStream out,String reqtypefile, int reqid);
}
