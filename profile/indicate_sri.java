/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package profile;

import java.sql.*;

import java.util.*;
import database.*;
public class indicate_sri {

    public void get_sri(String uname,String prof_uname,int vote,String state)

    {
        String updt_prof;
        try
        {
            Connection conn = ConnectionE.establishConn();
            Statement insrtstmt=conn.createStatement();
            

            String u1=prof_uname.substring(1,2);
            String b[]={"a","d","g","k","n","p","s","w"};
            //GET THE TABLE NAME TO WHICH THE USER WOULD BELONG TO
            for(int i=0;i<b.length;i++)
            {
                if (u1.compareToIgnoreCase(b[i])<0)
                {
                    u1=b[--i].toUpperCase();
                    break;
                }
            }
            String u=prof_uname.substring(0,1)+u1;

            int sri_prof=0;





//RETRIEVE THE SRI OF THE USER

         String sqld="select SRI from SOCIALITE.PD_"+state+" where uname='"+uname+"'";

        

        ResultSet rs=insrtstmt.executeQuery(sqld);

        
                int sri=0;
                    if(rs.next())
                    {
                        sri=Integer.parseInt(rs.getString(1));
             
                    }
                    else
                        return;

                System.out.println(sri);

//CHECK IF HE IS A REGISTERED MEMBER AND RETRIEVE SRI

                String sqld_prof="SELECT STATES FROM SOCIALITE.MASTER_TABLE_"+u.toUpperCase()+" WHERE UNAME='"+prof_uname+"'";
                System.out.println(sqld_prof);
                ResultSet rs3=conn.createStatement().executeQuery(sqld_prof);
                String prof_state=null;
                if(rs3.next())
                    prof_state=rs3.getString(1);

                sqld_prof="select SRI from SOCIALITE.PD_"+prof_state+" where uname ='"+prof_uname+"'";

                System.out.println(sqld_prof);


                    rs=insrtstmt.executeQuery(sqld_prof);
                   if(rs.next()){
                    sri_prof=rs.getInt(1);


                    

                }
                 
//WEIGHTED AVERAGE USED TO CALCULATE THE SRI


                sri_prof=(int) Math.ceil(sri_prof+vote*sri/(double)10);
		if( sri_prof > 10 )
		{
			sri_prof=10;
		}

                
                        updt_prof="update SOCIALITE.PD_"+prof_state+" set SRI ="+sri_prof+" where uname ='"+prof_uname+"'";
        System.out.println(updt_prof);

                insrtstmt.execute(updt_prof);

            System.out.println(sri_prof);
        }

        catch (Exception ex)
        {
            ex.getMessage();
        }

        }

    }
