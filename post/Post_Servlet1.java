/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

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
public class Post_Servlet1 extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {


            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            //Get all the parameters from the form
            // Session
            // String uname=request.getParameter("uname");
            HttpSession session=request.getSession(false);
            String uname = (String)session.getAttribute("uname");
            String title = request.getParameter("title");
            String tag = request.getParameter("tag");
            String xmltext = request.getParameter("post_text");
            tag=tag.trim();
            //out.println(".."+tag+"..");

            out.println("<html><head></head><body>");
            try {
                //Create entry for post and its XML file
                out.print("Hey");
                Post p = new Post();
                //p.insert_db("herat","EDUCATION_OTHERS","XML Challenge","Has it really worked??");
                p.insert_db(uname, tag, title, xmltext);
                /*Notify nfy=new Notify();
                nfy.notifyUsers(tag);*/
                response.sendRedirect("post.jsp");
                //Printing Success Message
                out.println("Successful");
                out.println("</body></html>");
            } catch (Exception e) {
                out.println(e.getMessage());
            } finally {
                out.close();
            }
            response.sendRedirect("profile.jsp?p="+uname);

        } catch (Exception ex) {
            Logger.getLogger(Post_Servlet1.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
