/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ngo;

import com.oreilly.servlet.*;
import java.io.*;
import java.util.*;
import com.oreilly.servlet.multipart.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Ngo_Serv extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session=request.getSession();
		String uname=(String)session.getAttribute("uname");
		PrintWriter out = response.getWriter();
		//String path = request.getRealPath("/WEB-INF/classes/ngo_files");
		String path=request.getRealPath("/WEB-INF");
		System.out.println(path);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Ngo_Serv</title>");
		out.println("</head>");
		out.println("<body>");

		String ngo_id = "NGO_" + (new Date().getTime() / 10000);
		MultipartRequest m = new MultipartRequest(request, path,
				5 * 1024 * 1024, new FileRename(ngo_id));

		if (new File(path + "\\" + ngo_id).mkdir());

		String name = m.getParameter("Name");
		String acro = m.getParameter("Acronymn");
		String desc = m.getParameter("desc");
		create_new n = new create_new();
		n.insrt_ngo(ngo_id, name, acro, desc,uname);

		get_members g = new get_members();
		g.reg_mem(path);

		out.println("</body>");
		out.println("</html>");

		out.close();
		response.sendRedirect("profile.jsp?p="+uname);
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
		processRequest(request, response);
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
		processRequest(request, response);
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
