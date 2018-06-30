package reglog;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.ibm.wkplc.httptunnel.inbound.impl.HttpRequestHandler;

import maketablename.*;
/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class Login extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
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
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		
		String init=MakeTableName.makeName(uname);
		System.out.println(init);
		//String temp=uname.substring(0, 2).toUpperCase();
		
		
		ResultSet rs[]=null;
		
		try {
			String[] pass=LoginDB.login(init, uname);
			if(pass == null)
			{
				//request.setAttribute("message", "User doesn't exit.");
				HttpSession session=request.getSession(true);
				session.setAttribute("error","User doesn't exist." );
				response.sendRedirect("https://localhost:9443/Integration/index.jsp");
			}
			else if(pass[0].equals(password))
			{
				System.out.println("Success..... :)");
				//Start session
				HttpSession session=request.getSession(true);
				session.setAttribute("uname", uname);
				session.setAttribute("state",pass[1]);
				response.sendRedirect("profile.jsp?p="+uname);
			}
			else
			{
				//response.setHeader("message", "Password doesn't match.");
				HttpSession session=request.getSession(true);
				session.setAttribute("error","Wrong Username/Password." );
				response.sendRedirect("https://localhost:9443/Integration/index.jsp");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} 
	
	
}