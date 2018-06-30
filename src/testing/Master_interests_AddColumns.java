/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shatadru
 */
public class Master_interests_AddColumns {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, ClassNotFoundException {
        try {
            String url = "jdbc:db2://localhost:50000/SOCILITE:retrieveMessagesFromServerOnGetMessage=true;";
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, "Herat", "123");
            Statement Stmt = conn.createStatement();
            File f = new File("C:\\Users\\Herat\\IBM\\rationalsdp7.0\\workspace\\Integration\\src\\Text_Files\\topics-revised.txt");
            String str = null;
            BufferedReader in = new BufferedReader(new FileReader(f));
            int i = 1;
            while ((str = in.readLine()) != null) {
                String sqls = "ALTER TABLE SOCIALITE.MASTER_INTERESTS ADD COLUMN A_" + i + " CHARACTER(1)";
                Stmt.execute(sqls);
                System.out.println("A_"+i);
                i++;
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Master_interests_AddColumns.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Master_interests_AddColumns.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
