/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package organisation;

import com.oreilly.servlet.*;
import java.io.*;
import java.util.*;
import com.oreilly.servlet.multipart.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrgAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String path = request.getRealPath("/WEB-INF");
        File f=new File(path+"\\org_files");
        if(!(f.isDirectory()))
        	f.mkdir();
        path=f.getPath();
        System.out.println(path);
        HttpSession session=request.getSession(false);
        
        String uname=(String)session.getAttribute("uname");
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet org_Serv</title>");
        out.println("</head>");
        out.println("<body>");

        //ADD TIMESTAMP TO THE ORGANISATION TO GIVE THEM UNIQUE NAMES
        String org_id = "ORG_" + (new Date().getTime() / 1000);

//USING A JAVA API TO PARSE MULTIPART POST DATA

        MultipartRequest m = new MultipartRequest(request, path, 5 * 1024 * 1024, new FileRename(org_id));

//GET THE PARAMETERS 
        String name = m.getParameter("org_name");
        String acro = m.getParameter("org_acro");
        String desc = m.getParameter("org_desc");

        //CREATING A NEW ORGANISATION
        create_new o = new create_new();
        o.insrt_org(org_id, name, acro, desc,uname);



        out.println("</body>");
        out.println("</html>");

        response.sendRedirect("profile.jsp?p="+uname);


        out.close();
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
