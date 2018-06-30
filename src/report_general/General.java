package report_general;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;  
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;  
import net.sf.jasperreports.engine.JasperReport;  
import net.sf.jasperreports.engine.design.JasperDesign;  
import net.sf.jasperreports.engine.xml.JRXmlLoader;  
import net.sf.jasperreports.view.JasperViewer;  

import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import database.ConnectionE;
import maketablename.*;

public class General {
	public static void runReport(String reportFile, String usr) {  
        try {  
            JasperDesign jasperDesign = JRXmlLoader.load(reportFile);  
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            Map parameters = new HashMap();
            parameters.put("uname", usr.toLowerCase());
                        
            Connection jdbcConnection = ConnectionE.establishConn();  
            JasperPrint jasperPrint = JasperFillManager.fillReport(  
                    jasperReport, parameters, jdbcConnection);
            
            //JasperViewer.viewReport(jasperPrint);
            JasperManager.printReportToPdfFile(jasperPrint, "USR_"+usr+".pdf");
            jdbcConnection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String usr=args[0];
			usr=usr.toUpperCase();
			
			String para[]=new String[2];
			para[0]=usr;
			
			String tmp=findState(usr);
			para[1]=tmp;
			
			CreateJRXML.main(para);
			String reportFile = "USR_"+usr+".jrxml";  
	        runReport(reportFile,usr); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String findState(String uname)
	{
		try
		{
			Connection conn=ConnectionE.establishConn();
			
			Statement stmt=conn.createStatement();
			String init=MakeTableName.makeName(uname);
			
			String sql="SELECT * FROM SOCIALITE.MASTER_TABLE_"+init+" WHERE UNAME='"+uname.toLowerCase()+"'";
			
			ResultSet rs=stmt.executeQuery(sql);
			String ans="";
			if(rs.next())
			{
				ans=rs.getString("STATES");
			}
			
			conn.close();
			return ans;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

}
