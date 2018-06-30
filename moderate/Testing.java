package moderate;

import java.sql.*;
import maketablename.MakeTableName;
import post.Post_Servlet1;
import database.*;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.*;

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
			String xml="";
			String postid="";
			String table="";
			int ind=0;
			
			Connection conn=ConnectionE.establishConn();
			String sql="SELECT * FROM SOCIALITE.INFLAMMABLE";
			
			String sql1;
			ResultSet rs1;
			Statement stmt1;
			
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				postid=rs.getString(1);
				ind=postid.lastIndexOf("_");
				table=postid.substring(0, ind);
				
				sql1="SELECT NOTIFICATION FROM SOCIALITE.MASTER_POST_"+table+" WHERE POST_ID='"+postid+"'";
				stmt1=conn.createStatement();
				rs1=stmt1.executeQuery(sql1);
				
				if(rs1.next())
				{
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					
					xml=rs1.getString(1);
					InputStream is = new ByteArrayInputStream(xml.getBytes());
					Document doc = db.parse(is);
					NodeList node = doc.getElementsByTagName("ptext");
					
					content = node.item(0).getTextContent();
				}
				
				double val = p.parse(content);
				if (val < thresh && val > 0) {
					System.out.println("Not inflammable " + val);
					
					stmt=conn.createStatement();
					sql="DELETE FROM SOCIALITE.INFLAMMABLE WHERE POST_ID='"+postid+"'";
					stmt.execute(sql);
				} else {
					String uname="",init="",state="";
					stmt=conn.createStatement();
					sql="SELECT UNAME FROM SOCIALITE.MASTER_POST_"+table+" WHERE POST_ID='"+postid+"'";
					ResultSet rslt=stmt.executeQuery(sql);
					ResultSet rr;
					int pwarn=0;
					if(rslt.next())
					{
						uname=rslt.getString(1);
						init=MakeTableName.makeName(uname);
						
						sql="SELECT STATES FROM SOCIALITE.MASTER_TABLE"+init.toUpperCase()+" WHERE UNAME='"+uname+"'";
						rr=stmt.executeQuery(sql);
						
						if(rr.next())
						{
							state=rr.getString(1);
						}
						sql="SELECT WARNINGS FROM SOCIALITE.PD_"+state+" WHERE UNAME='"+uname+"'";
						rr=stmt.executeQuery(sql);
						if(rr.next())
						{
							pwarn=rr.getInt(1);
						}
						pwarn++;
						
						sql="UPDATE SOCIALITE.PD_"+state+" SET WARNINGS="+pwarn+" WHERE UNAME='"+uname+"'";
						stmt.execute(sql);
					}
					System.out.println("Inflammable  " + val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
