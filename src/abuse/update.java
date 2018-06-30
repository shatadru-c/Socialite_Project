/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abuse;
import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shatadru
 */
public class update {

    public int warn_update(String shrt,String name,Connection conn)
    {
         int wrn = 0;
        try {
            String sqls = "SELECT STATES FROM MASTER_TABLE_" + shrt+" WHERE UNAME='"+name+"'";
            ResultSet rs = conn.createStatement().executeQuery(sqls);
           
            if (rs.next()) {
                String states = rs.getString(1);
                String sqls2 = "SELECT WARNINGS FROM PD_" + states + " WHERE UNAME='" + name + "'";
                ResultSet rs3 = conn.createStatement().executeQuery(sqls2);
                if (rs3.next()) {
                    wrn = rs3.getInt(1);
                }
                String sqls1 = "UPDATE PD_" + rs.getString(1) + " SET WARNINGS=" + (wrn + 1) + " WHERE UNAME='" + name + "'";
                System.out.println(sqls1);
                conn.createStatement().executeUpdate(sqls1);

                
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return ++wrn;
    }
}
