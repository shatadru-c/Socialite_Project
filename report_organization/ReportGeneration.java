package report_organization;

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
import database.*;


public class ReportGeneration {
	
	public static void runReport(String reportFile,String org) {  
        try {  
            JasperDesign jasperDesign = JRXmlLoader.load(reportFile);  
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            Map parameters = new HashMap();
            parameters.put("orgName", org);
            parameters.put("tableName", "ORG_"+org);
            
            Connection jdbcConnection = ConnectionE.establishConn();  
            JasperPrint jasperPrint = JasperFillManager.fillReport(  
                    jasperReport, parameters, jdbcConnection);
            
            //JasperViewer.viewReport(jasperPrint);
            JasperManager.printReportToPdfFile(jasperPrint, "ORG_"+org+".pdf");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	public static void main(String args[])
	{
		try
		{
			CreateJRXML.main(args);
			String org=args[0].toUpperCase();
			String reportFile = "ORG_"+org+".jrxml";  
	        runReport(reportFile,org); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
