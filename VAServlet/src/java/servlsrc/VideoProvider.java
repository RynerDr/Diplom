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
    void load(OutputStream out,String reqparam);
    //Параметр String reqparam является строкой и может быть забит на несколько
    //состовляющий, при необходимости. Так, в BaseHandler передаётся параметр типа
    // 3_video, после чего он разбивается на int id = 3 и String type = "video".
    //В LoaderStream через параметр передаётся HTTP адрес потока
    //необходимо закрыть выходной поток OutputStream out
    //            if (out != null) {
    //                out.close();
    //            }
}
