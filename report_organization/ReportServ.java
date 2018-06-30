package report_organization;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: Report
 * 
 */
public class ReportServ extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ReportServ() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String org[] = new String[1];
		org[0] = request.getParameter("org");
		ReportGeneration.main(org);
		download("ORG_"+org[0].toUpperCase(), response);
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Report Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Report Created...</h1>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void download(String filename, HttpServletResponse response) {
		try {
			String ori = filename + ".pdf";
			File f = new File(ori);
			int length = 0;
			ServletOutputStream op = response.getOutputStream();
			ServletContext context = getServletConfig().getServletContext();
			String mimetype = context.getMimeType(ori);
			response.setContentType((mimetype != null) ? mimetype
					: "application/octet-stream");
			response.setContentLength((int) f.length());
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + ".pdf" + "\"");
			byte[] bbuf = new byte[ori.length()];
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}
			in.close();
			op.flush();
			op.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}