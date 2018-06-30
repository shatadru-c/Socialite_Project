package post;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shatadru
 */

import database.ConnectionE;
import java.sql.*;
import java.util.*;

public class Post {
    
    public void insert_db(String uname, String tag,String title,String xmltext)
    {
                    try{
                        //Creating a JDBC connection
                        Connection conn=ConnectionE.establishConn();
                        String sqls = null;
                        
                        int id=0;
                        String post_id=null;
     
                        Statement insertStmt = conn.createStatement();

                        //Querying the current id number for the table
                        sqls="select COUNT(*) from SOCIALITE.MASTER_POST_"+tag;

                        ResultSet rs=insertStmt.executeQuery(sqls);

                        if(rs.next()==false)
                        {
                            id=0;
                        }
                        else
                        {
                            id=rs.getInt(1);
                            //System.out.println(m);
                                              
                        }
                            id++;

                            //Setting the new post id to the database

                            post_id=tag+"_"+id;

                            //Closing connection to the database
                            conn.close();

                            //Inserting the details of the entries of the post into the designated table

                            XMLtbl x=new XMLtbl();
                            x.CreateXML(post_id, title, xmltext,uname,tag);


                            Notify n=new Notify();
                            n.notifyUsers(tag, post_id);
                            System.out.println("\n\n"+tag+"----"+post_id);
                           
                            
                    }
    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
    }

}
