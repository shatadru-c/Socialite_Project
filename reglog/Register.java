package reglog;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: Register
 *
 */
 public class Register extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String uname=request.getParameter("username");
		String name=request.getParameter("name");
		String pass=request.getParameter("password");
		String address=request.getParameter("address");
		String contact=request.getParameter("contact");
		String email=request.getParameter("email");
		String proof=request.getParameter("proof");
		String number=request.getParameter("number");
		String state=request.getParameter("state");
		String inter[]=request.getParameterValues("interests");
		
		HttpSession mys1=request.getSession(true);
        String captcha = (String) mys1.getAttribute("captcha");
        String code = (String) request.getParameter("code");
        HttpSession session=request.getSession(true);
        
        PrintWriter out = response.getWriter();
        if (captcha != null && code != null) {

             if (captcha.equals(code)) {
            	 	out.print("Correct");
                 } else {
                	 out.print("Incorrect");
                	 session.setAttribute("error", "Incorrect");
                   response.sendRedirect("Registration.jsp");
          }
         }

		
		RegisterDB.registerDB(uname, name, pass, address, contact, email, proof, number, state,session);
		
		InsertInter.insertInter(uname, inter);
		response.sendRedirect("index.jsp");
	}   	  	    
}