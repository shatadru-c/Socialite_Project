package testing;
import java.sql.*;

import database.*;

public class DummyFriends {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Connection conn=ConnectionE.establishConn();
		String url=null;
			
		String uname="shat";
		String name="Shatadru";
		String pass="qwerty";
		String address="same";
		int cont=123263;
		String email="sds@ds.d";
		int sri=12;
		String noti="<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns:notification xmlns:tns=\"http://www.example.org/notification\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.example.org/notification notification.xsd \"></tns:notification>";
		int warn=12;
		String friend="<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns:friends xmlns:tns=\"http://www.example.org/friend\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.example.org/friend friends.xsd \"></tns:friends>";
		String msg="<?xml version=\"1.0\" encoding=\"UTF-8\"?> <tns:messages  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tns=\"http://www.example.org/message\" xsi:schemaLocation=\"http://www.example.org/message message.xsd \"></tns:messages>";
                Statement stmt=conn.createStatement();
		String sqls="INSERT INTO SOCIALITE.PD_GUJ VALUES ('"+uname+"','"+name+"','"+pass+"','"+address+"',"+cont+",'"+email+"',"+sri+",XMLVALIDATE(XMLPARSE(DOCUMENT '"+noti+"') ACCORDING TO XMLSCHEMA ID SOCIALITE.NOTIFICATION_XML),"+warn+",XMLVALIDATE(XMLPARSE(DOCUMENT '"+friend+"') ACCORDING TO XMLSCHEMA ID SOCIALITE.FRIENDS_XML),XMLVALIDATE(XMLPARSE(DOCUMENT '"+msg+"') ACCORDING TO XMLSCHEMA ID SOCIALITE.MESSAGE_XML))";
		stmt.execute(sqls);

		System.out.println("Done!!!");
		}

}
