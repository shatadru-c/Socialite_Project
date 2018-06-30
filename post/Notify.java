package post;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import database.ConnectionE;
import java.sql.*;

/**
 *
 * @author Shashank
 */
public class Notify {

    public void notifyUsers(String tag,String post_id) throws ClassNotFoundException {
        try {
            Connection conn=ConnectionE.establishConn();
            Statement insertStmt = conn.createStatement();

            String sqls = "select MAPPED from SOCIALITE.MASTER_POST where TAG='"+tag+"'";

            ResultSet rs = insertStmt.executeQuery(sqls);
            String mapped=null;

            //mapped=rs.getString("MAPPED");

            if(rs.next())
            {
                mapped=rs.getString("MAPPED");
            } 

            conn.close();

            insert_oders i=new insert_oders();
            i.inform(mapped,post_id);
            System.out.println("...."+mapped+"<--->"+post_id+".....");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
