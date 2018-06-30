/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package post;

import database.ConnectionE;
import java.sql.*;
import java.io.*;

/**
 *
 * @author Shatadru
 */
public class insert_oders {

    public void inform(String mapped,String post_id){
    try{
            Connection conn=ConnectionE.establishConn();
            Statement insertStmt=conn.createStatement();

            String sqls = "select * from SOCIALITE.MASTER_INTERESTS where +"+mapped+"='1'";

            ResultSet rs = insertStmt.executeQuery(sqls);

            String usrname=null;
           // File f=new File("C:\\Users\\Shatadru\\Documents\\NetBeansProjects\\Social\\poost_ID.TXT");
            
            File f=new File("\\WEB-INF\\classes\\Text_Files\\"+post_id+".TXT");
            BufferedWriter out=new BufferedWriter(new FileWriter(f));
            File f1=new File("\\WEB-INF\\classes\\Text_Files\\FileList.TXT");
            BufferedWriter out1=new BufferedWriter(new FileWriter(f1,true));
            out1.append(post_id+"\n");
            out1.close();
            while(rs.next())
            {
                usrname=rs.getString("UNAME");
                //System.out.println(usrname);
                out.write(usrname+"\n");
            }
            out.close();
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }
}
