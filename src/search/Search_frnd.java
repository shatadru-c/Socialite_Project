package search;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ConnectionE;

/**
 * Servlet implementation class for Servlet: Search_frnd
 *
 */
 public class Search_frnd extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Search_frnd() {
		super();
	}   	
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			HttpSession session=request.getSession();
			String uname=(String)session.getAttribute("uname");
			String state=(String)session.getAttribute("state");
			
			String names=getNames(uname, state);
			String unames[]=names.split("\n"); 
			
			out.println("<html><head></head><body>");
			int i;
			
			for(i=0;i<unames.length;i++)
			{
				
				out.println("<a href='profile.jsp?frndname='"+unames[i]+"'>"+unames[i]+"</a>");
				
			}
			
			out.println("</body></html>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static String getNames(String uname,String state) {
		String names = "";
		try {
			// Session uname
						
			Connection conn = ConnectionE.establishConn();
			Statement stmt = conn.createStatement();
			String sql = "select XMLQUERY('declare default element namespace \"http://www.example.org/friend\"; $tmp/friends/friend' passing SOCIALITE.PD_"+state+".FRIENDS as \"tmp\") from SOCIALITE.PD_"+state+" where UNAME like '"
					+ uname + "' ";
			String xml = "";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				xml = rs.getString(1);

			}
			String subs = xml;
			while (true) {
				int maini;
				int ind = subs.indexOf("</friend>");
				if (ind == -1) {
					break;
				}
				ind--;
				//System.out.println(subs.charAt(ind));
				maini = ind + 3;
				String ttt = "";
				while (subs.charAt(ind) != '>') {
					ttt += subs.charAt(ind);
					ind--;
				}
				subs = subs.substring(maini);
				//System.out.println(subs);

				StringBuffer sb = new StringBuffer(ttt);
				String as = sb.reverse().toString();
				names += as + "\n";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return names;
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}