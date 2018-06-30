package database;
import java.sql.*;

public class ConnectionE {
	public static Connection establishConn()
	{
		try 
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			Connection con111 = DriverManager.getConnection("jdbc:db2://localhost:50000/SOCILITE:retrieveMessagesFromServerOnGetMessage=true;","Herat","123");
			return con111;
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
