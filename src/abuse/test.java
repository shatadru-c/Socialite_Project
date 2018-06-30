/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abuse;
import java.sql.*;
import database.*;
import ngo.*;
/**
 *
 * @author Shatadru
 */
public class test {
    public static void main(String args[])
    {

        String name="SHAT";

        String shrt = "SG";
            Connection conn = null;
            String url = null;
            conn =ConnectionE.establishConn();
        new update().warn_update(shrt,name,conn);
    }

}
