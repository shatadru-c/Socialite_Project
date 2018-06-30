/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package organisation;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.ConnectionE;


/**
 *
 * @author Shatadru
 */
public class create_new {

    public void insrt_org(String org_id,String name,String acro,String desc,String uname){
        try {
            Connection conn = ConnectionE.establishConn();
            Statement insrtStmt = conn.createStatement();
//INSERTS THE TABLE INTO THE ORGANISATION MASTER TABLE
            String sqls = "INSERT INTO SOCIALITE.ORG_MASTER VALUES ('" + org_id + "','" + name + "','" + acro + "','" + desc + "','<ns1:org  xmlns:xsi=''http://www.w3.org/2001/XMLSchema-instance'' xmlns:ns1=''http://www.w3.org/2001/XMLSchema''></ns1:org>','"+uname+"')";
            System.out.println(sqls);
            insrtStmt.execute(sqls);
//CREATES A NEW INDIVIUAL TABLE FOR EACH ORGANISATION
            sqls="CREATE TABLE SOCIALITE."+org_id+"( NAME VARCHAR (50)  NOT NULL , STATE VARCHAR (4)  NOT NULL , SRI INTEGER  NOT NULL , DESIGNATION VARCHAR (50)  NOT NULL , PAN VARCHAR (25)  NOT NULL  , CONSTRAINT CC1300732745487 PRIMARY KEY ( PAN)  )";
            insrtStmt.execute(sqls);
            
        }
        catch (Exception ex) {
            ex.getMessage();
        }

    }

}
