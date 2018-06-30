package post;


import database.ConnectionE;
import java.io.*;
import java.sql.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shatadru
 */
public class XMLtbl {
    public void CreateXML(String post_id,String title,String xtext,String uname,String tag)
    {
        try {

            //Creating a JDBC Connection
            Connection conn=ConnectionE.establishConn();

            Statement insertStmt = conn.createStatement();

            String xmltext=null;

        //Assigning the entries of the post to a string so that it can be directly inserted into a table
        
        xmltext="<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns:post  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tns=\"http://www.example.org/post\" xsi:schemaLocation=\"http://www.example.org/post post.xsd\"> <tns:ptitle>"+title+"</tns:ptitle> <tns:ptext>"+xtext+"</tns:ptext> <tns:likes>0</tns:likes><tns:dislikes>0</tns:dislikes> </tns:post>";
        String sqls="INSERT INTO SOCIALITE.MASTER_POST_"+tag.toUpperCase()+" VALUES ('"+post_id+"','"+title+"','"+uname+"',XMLVALIDATE(XMLPARSE(DOCUMENT '"+xmltext+"') ACCORDING TO XMLSCHEMA ID SOCIALITE.POST_XML))";
        
        

        //Inserting contents
        insertStmt.execute(sqls);
        
        sqls="INSERT INTO SOCIALITE.INFLAMMABLE VALUES '"+tag.toUpperCase()+"'";
        insertStmt.execute(sqls);

        conn.close();
    }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
