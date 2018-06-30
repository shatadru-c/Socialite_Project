package moderate;

import database.*;
import java.sql.*;

public class Parse {
	Connection conn;
	
	public Parse()
	{
		conn=ConnectionE.establishConn();
	}
	
	public double parse(String content)
	{
		String ary[]=content.split(" ");
		int i;
		double sum=0;
		
		for(i=0;i<ary.length;i++)
		{
			sum += find(ary[i]);
		}
		return sum;
	}
	public double find(String word)
	{
		try
		{
			Statement stmt=conn.createStatement();
			String qry="SELECT * FROM SOCIALITE.WORDBASE WHERE WORD='"+word+"'";
			ResultSet rs=stmt.executeQuery(qry);
			double tmp=0;
			if(rs.next())
			{
				tmp += rs.getDouble("POSITIVITY");
				tmp -= rs.getDouble("NEGATIVITY");
				System.out.println(tmp);
				return tmp;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
