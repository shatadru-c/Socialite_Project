/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ngo;

import database.*;
import java.sql.*;

/**
 * 
 * @author Shatadru
 */
public class create_new {

	public void insrt_ngo(String ngo_id, String name, String acro, String desc,String uname) {
		try {
			Connection conn = ConnectionE.establishConn();
			Statement insrtStmt = conn.createStatement();
			String sqls = "INSERT INTO SOCIALITE.NGO_MASTER VALUES ('"
					+ ngo_id
					+ "','"
					+ name
					+ "','"
					+ acro
					+ "','"
					+ desc
					+ "','<ns1:ngo  xmlns:xsi=''http://www.w3.org/2001/XMLSchema-instance'' xmlns:ns1=''http://www.w3.org/2001/XMLSchema''></ns1:ngo>','"+uname+"')";
			System.out.println(sqls);
			insrtStmt.execute(sqls);

			sqls = "CREATE TABLE SOCIALITE.MOVEMENT_"
					+ ngo_id
					+ " (MOV_ID VARCHAR(25) NOT NULL,DATE DATE NOT NULL,TIME TIME NOT NULL,MOVEMENT XML, CONSTRAINT CC1300547638636 PRIMARY KEY ( MOV_ID)) ";

			insrtStmt.execute(sqls);
		} catch (Exception ex) {
			ex.getMessage();
		}

	}

}
