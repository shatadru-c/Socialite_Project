/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import database.ConnectionE;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 *
 * @author Shashank
 */
public class ViewPost extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //Session
        	HttpSession session=request.getSession(false);
            String uname = (String) session.getAttribute("uname");
            String state= (String) session.getAttribute("state");
            
            String comment = request.getParameter("comment");
            String text = request.getParameter("text");
            String pid = request.getParameter("pid");
            
            String splitArr[] = comment.split("~~~");
            int Count = 0;
            String str = "";
            while (Count < splitArr.length) {
                str += splitArr[Count];
                str += "<br>";
                Count += 1;
            }
            Connection conn = ConnectionE.establishConn();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * from SOCIALITE.PD_" + state + " where UNAME='" + uname + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            String Name = rs.getString("NAME");
            String sqls = "UPDATE SOCIALITE.MASTER_POST_" + text + " SET NOTIFICATION=XMLQUERY('declare default element namespace \"http://www.example.org/post\"; transform copy $temp := $tmp modify do insert document{<comments><uname>" + uname + "</uname><comment>" + comment + "</comment><likes>0</likes><dislikes>0</dislikes></comments>} as last into $temp/post return $temp' passing SOCIALITE.MASTER_POST_" + text + ".NOTIFICATION as \"tmp\") WHERE POST_ID='" + pid + "'";


            out.println("<h3 style=\"background-color:silver\">" + Name + ": </h3>");
            out.println(str + "<br>");

            stmt.execute(sqls);
            //response.sendRedirect("post_view.jsp");

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
