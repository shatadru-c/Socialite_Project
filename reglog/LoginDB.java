package reglog;
import java.sql.*;
import database.*;

public class LoginDB
{
	public static String[] login ( String initial,String uname ) throws SQLException, Exception
	{
		// Get connection to the database
		Connection con=ConnectionE.establishConn();
		Statement stmt = null;
		
		String sql;
		ResultSet rs;
		
		sql = "SELECT STATES FROM SOCIALITE.MASTER_TABLE_"+initial+" WHERE UNAME='"+uname+"'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		String state="";
		String passwd="";
		String ret[]=new String [2];
		if(rs.next())
		{
			state=rs.getString("STATES");
				
			sql = "SELECT * FROM SOCIALITE.PD_"+state+" WHERE UNAME='"+uname+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		
			if(rs.next())
			{
				passwd=rs.getString("PASSWORD");
				ret[0]=passwd;
				ret[1]=state;
			}
			else
			{
				
				return null;
			}
		}
		else
		{
			return null;
		}
		con.close();
		stmt.close();
		return ret;
	}
}