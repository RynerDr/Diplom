/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

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
    public String Load(String reqtypefile, int reqid) {
        String resultsearch = null;
         
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
        } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            PreparedStatement st = null;
        try {
            st = con.prepareStatement("SELECT id, urlfile FROM "+ reqtypefile + " where id=?");
        } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.setInt(1, reqid);
        } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //SQL запрос к базе данных, чтобы получить весь список интересующей таблицы   
            ResultSet rs = null;
        try {
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           try { 
            while (rs.next()) 
            {
                // записываем ответ на запрос
                    resultsearch=rs.getString("urlfile");
                    //закрытие
                    
            }
           }catch(SQLException ex) {
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
        return resultsearch;
    }

    

    
}
