/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import java.io.IOException;
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
    private String paramInitClass = null;

   @Override
      public void init (ServletConfig config) throws ServletException  
      {
          //инициализация начальних настроек сервлета
          this.paramInitClass = config.getInitParameter("videoProvider");

      }  
      
      @Override
      public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
          //выполнение метода service и передачае ему параметров запроса и ответа
          service(req,resp);
          
      }
      
     

     
     @Override
     public void service (ServletRequest req, ServletResponse resp) 
                           throws ServletException, IOException  
      { 

       try {
          VideoProvider provider = (VideoProvider) Class.forName(this.paramInitClass).newInstance(); 
          req.setCharacterEncoding("Cp1251");
          
            //получение параметров запроса
          String reqparam = req.getParameter("media");
               
          provider.load(resp.getOutputStream(), reqparam);
          } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
           Logger.getLogger(VAOutServl.class.getName()).log(Level.SEVERE, null, ex);
       }
      }

}
