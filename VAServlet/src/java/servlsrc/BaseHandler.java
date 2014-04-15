/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public String BHand (String reqtypefile, int reqid){
        String resultsearch = null;
        ResultSet rs = null;
         int indexreqbd = 0;
         ArrayList<Integer> idreqbd = new ArrayList<>();
         ArrayList<String> urlfilebd = new ArrayList<>();
        
         try {
                Context con = (Context) new InitialContext().lookup(LOOKUP);
                ds = (DataSource) con.lookup(RESOURCE);
             } catch (NamingException ex) {
                System.err.println(ex);
             }
         Connection con = null;
                

        try {
            con = ds.getConnection();
      
        } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            java.sql.Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            rs = st.executeQuery("SELECT id, urlfile FROM "+ reqtypefile);
        } catch (SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

           try { 
            while (rs.next()) 
            {
                // Напечатать значения в текущей строке.
                
                try {
                    idreqbd.add(rs.getInt("id"));
                    urlfilebd.add(rs.getString("urlfile"));
                } catch (SQLException ex) {
                    Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           }catch(SQLException ex) {
            Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i=0; i<idreqbd.size();i++){
            System.out.println(idreqbd.get(i) + urlfilebd.get(i));
            if(idreqbd.get(i)==reqid)resultsearch=urlfilebd.get(i);
        }
        
        return resultsearch;
    }
}
