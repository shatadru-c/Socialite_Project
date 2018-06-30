/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testing;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import database.*;
/**
 *
 * @author Shatadru
 */
public class writ_mapping {

    public static void main(String args[])
    {
        try
        {
            FileReader fr=new FileReader(new File("E:/topics-revised.txt"));
            FileReader fr1=new FileReader(new File("E:/topics.txt"));
            String s="",t;
            Connection conn=null;
            
            conn=ConnectionE.establishConn();
            Statement insrt=conn.createStatement();
            BufferedReader br=new BufferedReader(fr);
            BufferedReader br1=new BufferedReader(fr1);
            while((s=br.readLine())!=null)
            {
               String sqls="INSERT INTO SOCIALITE.POST_MAPPING VALUES ('"+(t=br1.readLine())+"','"+s+"')";
               System.out.println(t+"   "+s);
               insrt.execute(sqls);
            }

        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

}
