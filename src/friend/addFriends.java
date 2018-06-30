/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package friend;

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

import database.*;

/**
 * 
 * @author Shashank
 */
public class addFriends extends HttpServlet {

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
			ClassNotFoundException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Connection c = ConnectionE.establishConn();

		String url = null;
		HttpSession session=request.getSession(false);
		
		String usrname = (String)session.getAttribute("uname");
		// Get all the parameters from the form

		Statement stmt = c.createStatement();
		String name = request.getParameter("friend");
		String statename = request.getParameter("state");
		String arr[] = { "A", "D", "G", "K", "N", "P", "S", "W" };
		String sqls = "";

		out.println("<html><head></head><body>");
		try {

			out.println("Search Results:");

			String temp = (name.substring(0, 1)).toUpperCase();
			String sec = (name.substring(1, 2)).toUpperCase();
			int j = 0, fl = 0;
			while (sec.compareTo(arr[j]) >= 0) {

				if (sec.compareTo(arr[j]) == 0) {
					sec = arr[j];
					fl = 1;
					break;
				}

				if (j == 7) {
					sec = "W";
					fl = 1;
					break;
				}
				j++;
			}

			if (fl == 0) {
				sec = arr[j - 1];
			}

			String sql = "select XMLQUERY('declare default element namespace \"http://www.example.org/friend\"; $tmp/friends/friend' passing SOCIALITE.PD_"
					+ statename
					+ ".FRIENDS as \"tmp\") from SOCIALITE.PD_"
					+ statename
					+ " where UNAME like '" + usrname + "' ";

			// CODE TO FIND THE LIST OF FRIENDS ALREADY IN THE LIST

			String xml = "";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				xml = rs.getString(1);

			}
			String subs = xml;
			String uarr[] = new String[20];
			int count = 0;
			while (true) {
				int maini;
				int ind = subs.indexOf("</friend>");
				if (ind == -1) {
					break;
				}
				ind--;
				// System.out.println(subs.charAt(ind));
				maini = ind + 3;
				String ttt = "";
				while (subs.charAt(ind) != '>') {
					ttt += subs.charAt(ind);
					ind--;
				}
				subs = subs.substring(maini);
				// System.out.println(subs);

				StringBuffer sb = new StringBuffer(ttt);
				String as = sb.reverse().toString();
				// System.out.println(as);
				uarr[count] = as;
				count++;
			}
			String str = "";
			int i;
			for (i = 0; i < count; i++) {
				str += "'" + uarr[i] + "',";
			}
			str += "'" + usrname + "'";

			// CODE ENDS

			// NEXT CODE STARTS
			// THIS IS FOR FINDING THE USERNAMES PENDING FOR APPROVAL

			String sql2 = "select XMLQUERY('declare default element namespace \"http://www.example.org/notification\"; $tmp/notification' passing SOCIALITE.PD_GUJ.NOTIFICATION as \"tmp\") from SOCIALITE.PD_"+statename+" where UNAME like '"
					+ usrname + "' ";
			String xml2 = "";

			ResultSet rs2 = stmt.executeQuery(sql2);
			if (rs2.next()) {
				xml2 = rs2.getString(1);

			}

			// System.out.println(xml2);
			String uarr2[] = new String[20];
			int count2 = 0;
			String subs2 = xml2;
			while (true) {
				int maini2;
				int ind2 = subs2.indexOf("</outnewfriends>");
				if (ind2 == -1) {
					break;
				}
				ind2--;
				// System.out.println(subs.charAt(ind));
				maini2 = ind2 + 3;
				String ttt2 = "";
				while (subs2.charAt(ind2) != '>') {
					ttt2 += subs2.charAt(ind2);
					ind2--;
				}
				subs2 = subs2.substring(maini2);
				// System.out.println(subs);

				StringBuffer sb2 = new StringBuffer(ttt2);
				String as2 = sb2.reverse().toString();
				System.out.println(as2);
				uarr2[count2] = as2;
				count2++;
			}

			int i2 = 0;

			if (count2 > 1) {
				for (i2 = 0; i2 < count2 - 1; i2++) {
					str += "'" + uarr2[i2] + "',";
				}
				str += "'" + uarr2[i2] + "'";
			} else
				str += ",'" + uarr2[i2] + "'";

			// CODE ENDS
			
//			 NEXT CODE STARTS
			// THIS IS FOR FINDING THE USERNAMES PENDING FOR APPROVAL (INNEWFRIENDS)

			String sql3 = "select XMLQUERY('declare default element namespace \"http://www.example.org/notification\"; $tmp/notification' passing SOCIALITE.PD_"+statename+".NOTIFICATION as \"tmp\") from SOCIALITE.PD_"+statename+" where UNAME like '"
					+ usrname + "' ";
			String xml3 = "";

			ResultSet rs3 = stmt.executeQuery(sql3);
			if (rs3.next()) {
				xml3 = rs3.getString(1);

			}

			// System.out.println(xml2);
			String uarr3[] = new String[20];
			int count3 = 0;
			String subs3 = xml3;
			while (true) {
				int maini3;
				int ind3 = subs3.indexOf("</innewfriends>");
				if (ind3 == -1) {
					break;
				}
				ind3--;
				// System.out.println(subs.charAt(ind));
				maini3 = ind3 + 3;
				String ttt3 = "";
				while (subs3.charAt(ind3) != '>') {
					ttt3 += subs3.charAt(ind3);
					ind3--;
				}
				subs3 = subs3.substring(maini3);
				// System.out.println(subs);

				StringBuffer sb3 = new StringBuffer(ttt3);
				String as3 = sb3.reverse().toString();
				System.out.println(as3);
				uarr3[count3] = as3;
				count3++;
			}

			int i3 = 0;

			if (count3 > 1) {
				for (i3 = 0; i3 < count3 - 1; i3++) {
					str += "'" + uarr3[i3] + "',";
				}
				str += "'" + uarr3[i3] + "'";
			} else
				str += ",'" + uarr3[i3] + "'";

			// CODE ENDS


			sqls = "select * from SOCIALITE.PD_" + statename + " where NAME LIKE'" + name
					+ "' AND UNAME NOT IN (" + str + ")";
			//System.out.println(sqls);
			//sout.println(sqls);
			rs = stmt.executeQuery(sqls);
			int flag = 1;

			String uname = "";
			out
					.println("<form name=\"frnd\" action=\"entryFriends\" method=\"post\">");

			while (rs.next()) {
				uname = rs.getString("UNAME");

				out.print("<br><input type=\"radio\" name=\"uname\" value=\""
						+ uname + "\"/>");
				// out.print("Result "+count);
				// out.print("ame+"</h4>");
				// String state = rs.getString("STATES");

				out
						.println("&nbsp;&nbsp;&nbsp;Username  :  "
								+ uname
								+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: "
								+ name);
				out.println("\n\n");
				flag = 0;

			}
			if (flag == 0) {
				out.println("<br><br><input type=\"submit\" value=\"Add\" />");
			}
			out.println("</form>");
			if (flag == 1) {
				out.println("<br><br><h3>No match found</h3>");
			}

			rs.close();

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
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(addFriends.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(addFriends.class.getName()).log(Level.SEVERE,
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
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(addFriends.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(addFriends.class.getName()).log(Level.SEVERE,
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
