/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import database.ConnectionE;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Shashank
 */
public class SendMsg extends HttpServlet {

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
		HttpSession session=request.getSession(false);
		try {

			String usrname = (String)session.getAttribute("uname");
			String From = request.getParameter("to");
			String Title = request.getParameter("title");
			String Msg = request.getParameter("content");

			Connection conn = ConnectionE.establishConn();
			Statement stmt = conn.createStatement();

			String sql = "UPDATE SOCIALITE.PD_GUJ SET MESSAGE=XMLQUERY('declare default element namespace \"http://www.example.org/message\"; transform copy $temp := $tmp modify do insert document{<message><Title>"
					+ Title
					+ "</Title><From>"
					+ usrname
					+ "</From><Content>"
					+ Msg
					+ "</Content></message>} as first into ($temp/messages) return $temp' passing SOCIALITE.PD_GUJ.MESSAGE as \"tmp\") WHERE UNAME='"
					+ From + "'";
			out.println(sql);
			stmt.execute(sql);
			response.sendRedirect("profile.jsp?p="+usrname);
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
			Logger.getLogger(SendMsg.class.getName()).log(Level.SEVERE, null,
					ex);
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
			Logger.getLogger(SendMsg.class.getName()).log(Level.SEVERE, null,
					ex);
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
