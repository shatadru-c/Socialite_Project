/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package organisation;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import database.*;

/**
 * 
 * @author Shatadru
 */
public class testXmlretriev {
	public void insrt() {
		try {

			Connection conn = ConnectionE.establishConn();
			Statement insertStmt = conn.createStatement();
			// String sqls="UPDATE MASTER_POST_ELECTIONS SET
			// PATH=XMLQUERY('transform copy $temp := $tmp modify do insert
			// document{<comments>1v6</comments>} as last into $temp/post return
			// $temp' passing MASTER_POST_ELECTIONS.PATH as \"tmp\") WHERE
			// POST_ID='ELECTIONS_2'";
			// insertStmt.execute(sqls);

			String sqlr = "select xmlquery('$c/post/likes' passing path as \"c\") from SOCIALITE.MASTER_POST_ELECTIONS where POST_ID='ELECTIONS_1'";
			ResultSet rs = insertStmt.executeQuery(sqlr);
			if (rs.next()) {
				Pattern p = Pattern.compile("[0-9]");
				Matcher m = p.matcher(rs.getString(1));
				m.find();
				int like = Integer.parseInt(m.group());

			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}
