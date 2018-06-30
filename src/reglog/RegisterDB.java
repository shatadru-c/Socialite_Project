package reglog;
import java.sql.*;
import database.*;
import maketablename.*;
import javax.servlet.http.*;

public class RegisterDB 
{
	public static void registerDB(String uname,String name,String pass,String address,String contact,String email,String proof,String number, String state, HttpSession session)
	{
		try
		{
			Connection con = ConnectionE.establishConn();
			Statement stmt = null;
			String sql;
			ResultSet rs;
			String initial = MakeTableName.makeName(uname);
			
			sql = "SELECT STATES FROM SOCIALITE.MASTER_TABLE_"+initial+" WHERE UNAME='"+uname+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				
				System.out.println("UNAME already exists...");
				session.setAttribute("error", "User already exists");
				return;
			}
			
			sql="SELECT * FROM SOCIALITE.VER_"+state+"  WHERE "+proof.toUpperCase()+"='"+number+"'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				System.out.println("User already registered...");
				session.setAttribute("error", "User already exists");
				return;
			}
			
			//sql="SELECT * FROM SHATADRU.GUV_VER_"+state+" WHERE UNAME='"+uname+"' AND "+proof.toUpperCase()+"='"+number+"'";
			sql="SELECT * FROM SOCIALITE.GUV_VER_"+state+" WHERE "+ proof.toUpperCase()+" = '"+ number +"'";
			stmt=con.createStatement();
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				//sql="INSERT INTO SOCIALITE.PD_"+state+" (UNAME, NAME, PASSWORD, ADDRESS, CONTACT,EMAIL, SRI) VALUES ('"+uname+"', '"+name+"', '"+pass+"', '"+address+"', "+contact+", '"+email+"', 0) ";
				String noti="<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns:notification xmlns:tns=\"http://www.example.org/notification\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.example.org/notification notification.xsd \"></tns:notification>";
				String friend="<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns:friends xmlns:tns=\"http://www.example.org/friend\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.example.org/friend friends.xsd \"></tns:friends>";
				String msg="<?xml version=\"1.0\" encoding=\"UTF-8\"?> <tns:messages  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tns=\"http://www.example.org/message\" xsi:schemaLocation=\"http://www.example.org/message message.xsd \"></tns:messages>";
				String sqls="INSERT INTO SOCIALITE.PD_"+state+" VALUES ('"+uname+"','"+name+"','"+pass+"','"+address+"',"+contact+",'"+email+"',"+0+",XMLVALIDATE(XMLPARSE(DOCUMENT '"+noti+"') ACCORDING TO XMLSCHEMA ID SOCIALITE.NOTIFICATION_XML),"+0+",XMLVALIDATE(XMLPARSE(DOCUMENT '"+friend+"') ACCORDING TO XMLSCHEMA ID SOCIALITE.FRIENDS_XML),XMLVALIDATE(XMLPARSE(DOCUMENT '"+msg+"') ACCORDING TO XMLSCHEMA ID SOCIALITE.MESSAGE_XML))";
				stmt=con.createStatement();
				System.out.println(sqls);
				stmt.execute(sqls);
				
				sql="INSERT INTO SOCIALITE.MASTER_TABLE_"+initial+" (UNAME,STATES) VALUES ('"+uname+"','"+state+"')";
				System.out.println(sql);
				stmt=con.createStatement();
				stmt.executeUpdate(sql);
				
				sql="INSERT INTO SOCIALITE.VER_"+state+" VALUES ('"+uname+"','"+rs.getString("LICENSE")+"','"+rs.getString("PASSPORT")+"','"+rs.getString("ADHAAR")+"','"+rs.getString("VOTER")+"','"+rs.getString("PAN")+"')";
				System.out.println(sql);
				stmt=con.createStatement();
				stmt.executeUpdate(sql);
							
			}
			else
			{
				System.out.println("User not in government database...");
				session.setAttribute("error", "User not in government database...");
				return;
			}
					
			stmt.close();
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
