package sentan;

import java.sql.*;
import database.*;

public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			double thresh = 0.35;
			Parse p = new Parse();
			String content = "";
			String postid="C_5";
			double val = p.parse(content);
			if (val < thresh && val > 0) {
				System.out.println("Not inflammable " + val);
				//Connection conn=ConnectionE.establishConn();
				//Statement stmt=conn.createStatement();
				//String sql="DELETE FROM SHATADRU.INFLAMMABLE WHERE POST_ID='"+postid+"'";
				//stmt.execute(sql);
			} else {
				System.out.println("Inflammable  " + val);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
