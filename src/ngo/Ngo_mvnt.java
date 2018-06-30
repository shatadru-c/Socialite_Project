/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ngo;

/**
 *
 * @author Shatadru
 */

import database.*;

import java.util.*;
import java.io.*;
import java.sql.*;

public class Ngo_mvnt {

    public void start_mov(String name,String desc,String time,String date,String ngo_id)
    {
       try{
        String url=null;
        Connection conn=ConnectionE.establishConn();
        String mov_id="mov_"+(new java.util.Date().getTime());
        Statement insrt=conn.createStatement();
        String sqls="INSERT INTO SOCIALITE.MOVEMENT_"+ngo_id+" values ('"+mov_id+"','"+date+"','"+time+"','<ns1:movement  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ns1=\"http://www.w3.org/2001/XMLSchema\"><desc>"+desc+"</desc></ns1:movement>')";
        //System.out.println(sqls);
        insrt.execute(sqls);
        
       
        }
       catch(Exception e)
       {
           e.printStackTrace();
       }
        

    }


}
