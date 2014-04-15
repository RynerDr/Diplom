/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
public class VAServlMain extends HttpServlet {
   String pageVideo = "VideoResponse.jsp";
   String pageAudio = "AudioResponse.jsp";
     
   @Override
      public void init (ServletConfig config) throws ServletException  
      {  
      }  
      
      @Override
      public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
          service(req,resp);
      }
      
     @Override
      public void destroy() {
      }  

     
     @Override
     public void service (ServletRequest req, ServletResponse resp) 
                           throws ServletException, IOException  
      {  
        String resultsearch = null;
        BaseHandler bh = new BaseHandler();
           req.setCharacterEncoding("Cp1251");
            String id = req.getParameter("id");
            String typefile = req.getParameter("typefile");
            resultsearch=bh.BHand(typefile, Integer.parseInt(id));
            req.setAttribute("urladdres", resultsearch);
            if (typefile.equals("video")){
                 RequestDispatcher dispatcher = req.getRequestDispatcher(pageVideo);
                   
                 if (dispatcher != null) {
                        dispatcher.forward(req, resp);
                     }
            }
            if (typefile.equals("audio")){
                 RequestDispatcher dispatcher = req.getRequestDispatcher(pageAudio);
 
                    if (dispatcher != null) {
                        dispatcher.forward(req, resp);
                     }
            }
    }
}