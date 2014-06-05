/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlsrc;

import java.io.*;
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
    private static final String RESOURCE = "jdbc/VAServlet";
    private static final String LOOKUP = "java:/comp/env";
    private DataSource ds;
    private String type;
    private int id;
    
    public void requestHandler (String reqmedia){
        id=Integer.parseInt(reqmedia.substring(0, reqmedia.indexOf("_")));
        type=reqmedia.substring(reqmedia.indexOf("_")+1);
    }

    @Override
    public void load(OutputStream out,String reqparam) {
        
        requestHandler(reqparam);
        final int size = 262144;//1048576 много 524288 тормозит 32768 близко 65536 лучше 98304 ещё лучше
        Connection  con = null;
        PreparedStatement st = null;
        ResultSet rs = null; 

        BufferedInputStream bis = null; 

         try {
            //загрузка контеста в котором описывается база данных
            Context context = (Context) new InitialContext().lookup(LOOKUP);
            ds = (DataSource) context.lookup(RESOURCE);
             con = ds.getConnection();
             st = con.prepareStatement("SELECT id, urlfile FROM "+ type + " where id=?");
             st.setInt(1,id);
 

            //SQL запрос к базе данных, чтобы получить весь список интересующей таблицы   
             rs = st.executeQuery();
         
            String resultsearch = null;
            while (rs.next()) {
                 // записываем ответ на запрос

                resultsearch = rs.getString("urlfile");
             }

            File newDirs  = new File(resultsearch);
             bis = new BufferedInputStream(new FileInputStream(newDirs));

            
             byte[] buffer = new byte[size];

            while ((bis.read(buffer)) != -1) {
                out.write(buffer);
            }
        } catch (NamingException | SQLException | IOException ex) {
             Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
             try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (SQLException | IOException ex) {
                 Logger.getLogger(BaseHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }

    

    
}
