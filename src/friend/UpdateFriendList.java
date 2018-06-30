/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package friend;

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
public class UpdateFriendList extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	
        	HttpSession session=request.getSession();
        	
            String usrname = (String)session.getAttribute("uname");
            String statename=(String)session.getAttribute("state");
            String bvalue=request.getParameter("button");
            //out.println(request.getParameterValues("button2"));
            String friend = request.getParameter("friend");
            Connection conn = ConnectionE.establishConn();
            Statement stmt = conn.createStatement();
            String arr[] = {"A", "D", "G", "K", "N", "P", "S", "W"};
            String Tofind=friend;
            String temp = (Tofind.substring(0, 1)).toUpperCase();
            String sec = (Tofind.substring(1, 2)).toUpperCase();
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
            String fstate="";
            String sql="SELECT * FROM SOCIALITE.MASTER_TABLE_"+temp+sec+" where UNAME='"+friend+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                fstate=rs.getString("STATES");
            }

            out.println(usrname+"--"+statename);
            out.println(friend+"--"+fstate);
            if(bvalue.equals("add"))
            {
                sql = "UPDATE SOCIALITE.PD_"+statename+" SET FRIENDS = XMLQUERY('declare default element namespace \"http://www.example.org/friend\"; transform copy $temp := $tmp modify do insert document{<friend>" + friend + "</friend>} as last into $temp/friends return $temp' passing SOCIALITE.PD_"+statename+".FRIENDS as \"tmp\") WHERE UNAME='"+usrname+"'";
                stmt.execute(sql);
                System.out.println(sql);
                sql = "UPDATE SOCIALITE.PD_"+fstate+" SET FRIENDS = XMLQUERY('declare default element namespace \"http://www.example.org/friend\"; transform copy $temp := $tmp modify do insert document{<friend>" + usrname + "</friend>} as last into $temp/friends return $temp' passing SOCIALITE.PD_"+fstate+".FRIENDS as \"tmp\") WHERE UNAME='"+friend+"'";
                stmt.execute(sql);
                System.out.println(sql);
            }
            
            sql="UPDATE SOCIALITE.PD_"+statename+" set NOTIFICATION = xmlquery( 'declare default element namespace \"http://www.example.org/notification\"; transform copy $new := $info modify do delete for $i in $new/notification/innewfriends where normalize-space(string($i/text()))=''"+friend+"'' return $i return $new' passing SOCIALITE.PD_"+statename+".NOTIFICATION as \"info\" ) WHERE UNAME='"+usrname+"'";
            stmt.execute(sql);
            System.out.println(sql);
            sql="UPDATE SOCIALITE.PD_"+fstate+" set NOTIFICATION = xmlquery( 'declare default element namespace \"http://www.example.org/notification\"; transform copy $new := $info modify do delete for $i in $new/notification/outnewfriends where normalize-space(string($i/text()))=''"+usrname+"'' return $i return $new' passing SOCIALITE.PD_"+fstate+".NOTIFICATION as \"info\" ) WHERE UNAME='"+friend+"'";
            stmt.execute(sql);
            System.out.println(sql);

            out.println("Done!!!!");
            response.sendRedirect("profile.jsp?p="+usrname);
            //response.sendRedirect("FriendNotification.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFriendList.class.getName()).log(Level.SEVERE, null, ex);
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
