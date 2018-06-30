/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testing;

import database.ConnectionE;
import java.sql.*;

/**
 *
 * @author Shashank
 */
public class Entries {
    public static void main(String args[]) throws SQLException
    {
       
        //Get all the parameters from the form
        Connection conn = ConnectionE.establishConn();
	    Statement stmt = conn.createStatement();
        String fname = "sjuyal";
        String usrname="mac";
        //System.out.println(fname);

        try {

            String sqls = "UPDATE SOCIALITE.PD_GUJ SET FRIENDS=XMLQUERY('declare default element namespace \"http://www.example.org/friend\"; transform copy $temp := $tmp modify do insert document{<friend>" + fname + "</friend>} as last into $temp/friends return $temp' passing SOCIALITE.PD_GUJ.FRIENDS as \"tmp\") WHERE UNAME='"+usrname+"'";

            //System.out.println(sqls);
            stmt.execute(sqls);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
