/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ngo;

/**
 * 
 * @author Shatadru
 */
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

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

	public void readFile(String fileName, String fname) {
		// ArrayList dataList = null;
		String name = "";
		String since = "";
		String uname = "";
		Connection conn = ConnectionE.establishConn();

		try {
			// dataList = new ArrayList();
			Statement insrtStmt = conn.createStatement();
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
						if (j == 0)
							name = xlsCell.getContents().toString();
						else if (j == 1)
							since = xlsCell.getContents().toString();
						else if (j == 2)
							uname = xlsCell.getContents().toString();

						// System.out.println("Cell
						// "+(i*(j+1))+xlsCell.getContents().toString());
					}
					String sqls = "UPDATE SOCIALITE.NGO_MASTER SET MEMBERS=XMLQUERY('declare default element namespace \"http://www.w3.org/2001/XMLSchema\";transform copy $temp := $tmp modify do insert document{<members><name>"
							+ name
							+ "</name><since>"
							+ since
							+ "</since><uname>"
							+ uname
							+ "</uname></members>} as last into $temp/ngo return $temp' passing SOCIALITE.NGO_MASTER.MEMBERS as \"tmp\") WHERE NGO_ID='"
							+ fname + "'";
					insrtStmt.execute(sqls);
					
					System.out.println(sqls);
					name = since = uname = null;
				}
			}
		} catch (Exception exec) {
			System.out.print("Exception " + exec);
		}
		// return dataList;
	}
}
