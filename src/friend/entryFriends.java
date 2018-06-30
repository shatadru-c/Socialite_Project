/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package friend;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.*;

/**
 * 
 * @author Shashank
 */
public class entryFriends extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		Connection c=ConnectionE.establishConn();
		String url = null;
		// Get all the parameters from the form
	
		Statement stmt = c.createStatement();
		String uname = request.getParameter("uname");
		HttpSession session=request.getSession(false);
		String usrname = (String)session.getAttribute("uname");
		String state=(String)session.getAttribute("state");
		out.println(uname);

		out.println("<html><head></head><body>");
		try {

			String sqls = "UPDATE SOCIALITE.PD_"+state+" SET NOTIFICATION=XMLQUERY('declare default element namespace \"http://www.example.org/notification\"; transform copy $temp := $tmp modify do insert document{<outnewfriends>"
					+ uname
					+ "</outnewfriends>} as last into ($temp/notification) return $temp' passing SOCIALITE.PD_GUJ.NOTIFICATION as \"tmp\") WHERE UNAME LIKE '"
					+ usrname + "'";
			stmt.execute(sqls);
			sqls = "UPDATE SOCIALITE.PD_"+state+" SET NOTIFICATION=XMLQUERY('declare default element namespace \"http://www.example.org/notification\"; transform copy $temp := $tmp modify do insert document{<innewfriends>"
					+ usrname
					+ "</innewfriends>} as last into ($temp/notification) return $temp' passing SOCIALITE.PD_GUJ.NOTIFICATION as \"tmp\") WHERE UNAME LIKE '"
					+ uname + "'";
			stmt.execute(sqls);

			response.sendRedirect("profile.jsp?p="+uname);
			out.println("</body></html>");

		} catch (Exception e) {
			out.println(e.getMessage());
			e.printStackTrace();
		} finally {

			out.close();

		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(entryFriends.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(entryFriends.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
