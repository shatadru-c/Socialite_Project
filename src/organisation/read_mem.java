/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package organisation;

/**
 *
 * @author Shatadru
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ngo.*;
import jxl.*;
import java.io.*;
import java.util.*;
import java.sql.*;

import database.ConnectionE;

/**
 *
 * @author Shatadru
 */
public class read_mem {

    public String readFile(String fileName,String fname) {
        //ArrayList dataList = null;
        String name="";
        String designation="";
        String state="";
        String pan="";
        String uname="";
        Connection conn=ConnectionE.establishConn();
        String result="";

        try {
            //dataList = new ArrayList();

            Statement insrtStmt=conn.createStatement();
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            String sheetName[] = workbook.getSheetNames();
            Sheet sheet;
            Cell xlsCell;
            Cell[] cell;

            for (int p = 0; p < sheetName.length; p++) {
                sheet = workbook.getSheet(p);
                for (int i = 1; i < sheet.getRows(); i++) {
                    cell = sheet.getRow(i);
                    for (int j = 0; j < cell.length; j++) {
                        xlsCell = sheet.getCell(j, i);
                            if(j==0)
                                name=xlsCell.getContents().toString();
                            else if(j == 1)
                                designation=xlsCell.getContents().toString();
                            else if(j==2)
                                uname=xlsCell.getContents().toString();
                            else if(j==3)
                                state=xlsCell.getContents().toString();
                            else if(j==4)
                                pan=xlsCell.getContents().toString();

                        

                       
                    }

                    String sqls1="SELECT UNAME FROM SOCIALITE.VER_"+state+" WHERE PAN="+pan+"";
                    ResultSet rs1=insrtStmt.executeQuery(sqls1);

                    if(rs1.next())
                        uname=rs1.getString(1);

                    

                    String sqls="UPDATE SOCIALITE.ORG_MASTER SET MEMBERS=XMLQUERY('declare default element namespace \"http://www.w3.org/2001/XMLSchema\";transform copy $temp := $tmp modify do insert document{<member><name>"+name+"</name><designation>"+designation+"</designation><uname>"+uname+"</uname><state>"+state+"</state><pan>"+pan+"</pan></member>} as last into $temp/org return $temp' passing SOCIALITE.ORG_MASTER.MEMBERS as \"tmp\") WHERE ORG_ID='"+fname+"'";

                    insrtStmt.execute(sqls);

                    sqls="SELECT ACRO FROM SOCIALITE.ORG_MASTER WHERE ORG_ID='"+fname+"'";
                    rs1=insrtStmt.executeQuery(sqls);

                    if(rs1.next())
                        sqls="INSERT INTO SOCIALITE."+fname+" VALUES ('"+name+"','"+state+"',5,'"+designation+"','"+pan+"')";
                    
                    result+=name+" has been added to the organisation"+rs1.getString(1)+"<br>";

                    insrtStmt.execute(sqls);

                    name=state=designation=pan=uname=null;
                }
                                        
                                        
            }
            
        } catch (Exception exec) {
            System.out.print("Exception " + exec);
        }
     return result;
    }
}
