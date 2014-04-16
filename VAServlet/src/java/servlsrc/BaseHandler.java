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
public class BaseHandler {
    private final String RESOURCE = "jdbc/VAServlet";
    private final String LOOKUP = "java:/comp/env";
    private DataSource ds;
    
    public String BHand (String reqtypefile, int reqid) throws SQLException{
        String resultsearch = null;
         
        //загрузка контеста в котором описывается база данных
         try {
                Context con = (Context) new InitialContext().lookup(LOOKUP);
                ds = (DataSource) con.lookup(RESOURCE);
             } catch (NamingException ex) {
                System.err.println(ex);
             }
         //установка соединения

            Connection  con = ds.getConnection();
            
            PreparedStatement st = con.prepareStatement("SELECT id, urlfile FROM "+ reqtypefile + " where id=?");
            st.setInt(1, reqid);
            
        //SQL запрос к базе данных, чтобы получить весь список интересующей таблицы   
            ResultSet rs = st.executeQuery();
        
           try { 
            while (rs.next()) 
            {
                // записываем ответ на запрос
                    resultsearch=rs.getString("urlfile");
                    //закрытие
                    con.close();
                    rs.close();
                    st.close();
            }
           }catch(SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultsearch;
    }
}
