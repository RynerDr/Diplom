/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Ryner
 */
public class BaseHandler implements VideoProvider{
    private final String RESOURCE = "jdbc/VAServlet";
    private final String LOOKUP = "java:/comp/env";
    private DataSource ds;
    
    

    @Override
    public void load(OutputStream out,String reqtypefile, int reqid) {
        String resultsearch = null;
        PreparedStatement st = null;
        ResultSet rs = null; 
        
        //загрузка контеста в котором описывается база данных
         try {
                Context con = (Context) new InitialContext().lookup(LOOKUP);
                ds = (DataSource) con.lookup(RESOURCE);
             } catch (NamingException ex) {
                System.err.println(ex);
             }
         //установка соединения

            Connection  con = null;
        try {
            con = ds.getConnection();
            st = con.prepareStatement("SELECT id, urlfile FROM "+ reqtypefile + " where id=?");
            st.setInt(1, reqid);

        //SQL запрос к базе данных, чтобы получить весь список интересующей таблицы   
            rs = st.executeQuery();
        
            while (rs.next()) 
            {
                // записываем ответ на запрос
                    resultsearch=rs.getString("urlfile");
                    //закрытие
                    
            }
            } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                con.close();
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

           File newDirs ;
           int cur;
           newDirs = new File(resultsearch);
           FileInputStream qwe;
           BufferedInputStream bis = null; 
           
        try {
            qwe = new FileInputStream(newDirs);
            bis = new BufferedInputStream(new FileInputStream(newDirs));
            
            while((cur=bis.read())!=-1 )out.write(cur);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                out.close();
                bis.close();
                 } catch (IOException ex) {
                Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

    
}
