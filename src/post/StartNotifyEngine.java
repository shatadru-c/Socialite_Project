/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import database.ConnectionE;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Shatadru
 */
public class StartNotifyEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {

        Connection conn = ConnectionE.establishConn();
        Statement Stmt = conn.createStatement();

        String xmltext = null;
        
        File f = new File("\\WEB-INF\\classes\\Text_Files\\FileList.TXT");
        BufferedReader in = new BufferedReader(new FileReader(f));
        String fname = null;
        String uname = null;
        String arr[] = {"A", "D", "G", "K", "N", "P", "S", "W"};
        String sqls = "";
        while ((fname = in.readLine()) != null) {
            File f1 = new File("\\WEB-INF\\classes\\Text_Files\\" + fname + ".TXT");
            BufferedReader in1 = new BufferedReader(new FileReader(f1));
            String t = "";
            String text = null;
            StringTokenizer tok = new StringTokenizer(fname);
            int i = tok.countTokens();
            for (int j = 0; j <= i; j++) {
                t += tok.nextToken("_") + "_";
            }
            text = t.substring(0, (t.length() - 1));

            System.out.println(text);

            while ((uname = in1.readLine()) != null) {
                String temp = (uname.substring(0, 1)).toUpperCase();
                String sec = (uname.substring(1, 2)).toUpperCase();
                int j = 0, fl = 0;
                while (sec.compareTo(arr[j]) >= 0) {

                    if (sec.compareTo(arr[j]) == 0) {
                        sec = arr[j];
                        fl = 1;
                        break;
                    }

                    if (j == 7) {
                        sec = "W";
                        fl = 1;
                        break;
                    }
                    j++;
                }

                if (fl == 0) {
                    sec = arr[j - 1];
                }

                System.out.println(sec);
                sqls = "select * from SOCIALITE.MASTER_TABLE_" + temp + sec + " where UNAME='" + uname + "'";
                ResultSet rs = Stmt.executeQuery(sqls);
                rs.next();
                String state = rs.getString("STATES");
                System.out.println(state);

                //Assigning the entries of the post to a string so that it can be directly inserted into a table
                //xmltext = "<ns1:post xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ns1=\"http://www.w3.org/2001/XMLSchema\"><" + text + "><pid>" + fname + "</pid></" + text + "></ns1:post>";

                // System.out.println(uname+" "+fname);
                //sqls = "UPDATE PD_"+state+" SET NOTIFICATION=XMLQUERY('transform copy $temp := $tmp modify do insert document{<" + text + "><pid>" + fname + "</pid></" + text + ">} as last into $temp return $temp' passing PD_"+state+".NOTIFICATION as \"tmp\") WHERE UNAME='"+uname+"'";

                sqls = "UPDATE SOCIALITE.PD_" + state + " SET NOTIFICATION=XMLQUERY('declare default element namespace \"http://www.example.org/notification\"; transform copy $temp := $tmp modify do insert document{<" + text + "><pid>" + fname + "</pid></" + text + ">} as last into ($temp/notification) return $temp' passing SOCIALITE.PD_" + state + ".NOTIFICATION as \"tmp\") WHERE UNAME='" + uname + "'";
                System.out.println(sqls);
                Stmt.execute(sqls);
            }

            in1.close();
            f1.delete();
        }
        in.close();
        f.delete();
        
    }
}
