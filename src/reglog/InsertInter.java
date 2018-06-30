package reglog;

import java.sql.*;
import database.*;

public class InsertInter {
	
	public static void insertInter(String uname,String inte[])
	{
		try
		{
			Connection conn=ConnectionE.establishConn();
			int i;
			String sql="";
			String tmp="";
			String tmpv="";
			for(i=0;i<inte.length;i++)
			{
				tmp += inte[i];
				tmpv += "'1'";
				if(i != inte.length-1)
				{
					tmp+=",";
					tmpv+=",";
				}
			}
			sql="INSERT INTO SOCIALITE.MASTER_INTERESTS (UNAME,"+tmp+") VALUES ('"+uname+"',"+tmpv+")";
			System.out.println(sql);
			Statement stmt=conn.createStatement();
			
			stmt.execute(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
