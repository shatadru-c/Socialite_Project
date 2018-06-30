/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abuse;
import java.io.*;
import java.sql.*;
import java.util.*;
import database.*;
/**
 *
 * @author Shatadru
 */
public class ReportAbuse {

    public void sent_rep(String uname, String prof_name, String complaint,String link ) throws SQLException
    {
        Connection conn=null;
        String url=null;

        conn=ConnectionE.establishConn();

        Statement insrtStatement=conn.createStatement();
        String sqls="INSERT INTO SOCIALITE.ABUSE VALUES ('"+uname+"','"+prof_name+"','"+complaint+"','"+link+"')";
        
        insrtStatement.execute(sqls);
        System.out.println(sqls);

    }
    

}
