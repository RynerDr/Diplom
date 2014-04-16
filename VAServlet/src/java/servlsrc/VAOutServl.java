/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import VideoPriverPackage.VideoProvider;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ryner
 */
public class VAOutServl extends HttpServlet {

   @Override
      public void init (ServletConfig config) throws ServletException  
      {
          //инициализация начальних настроек сервлета
      }  
      
      @Override
      public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
          //выполнение метода service и передачае ему параметров запроса и ответа
          service(req,resp);
          
      }
      
     @Override
      public void destroy() {
      //деструктор
      }  

     
     @Override
     public void service (ServletRequest req, ServletResponse resp) 
                           throws ServletException, IOException  
      { 
          VideoProvider urlfile =new VideoProvider();
          File newDirs = null;
          int cur = 0;
          req.setCharacterEncoding("Cp1251");
            //получение параметров запроса
          String id = req.getParameter("id");
          String typefile = req.getParameter("typefile");

       try {
           newDirs = new File(urlfile.Load(typefile, Integer.parseInt(id)));
       } catch (SQLException ex) {
           Logger.getLogger(VAOutServl.class.getName()).log(Level.SEVERE, null, ex);
       }
        
          FileInputStream qwe = new FileInputStream(newDirs);
          BufferedInputStream bis = new BufferedInputStream(new FileInputStream(newDirs));
          OutputStream os = resp.getOutputStream();
          while((cur=bis.read())!=-1 )os.write(cur);
          os.close();
          bis.close();
      }

}
