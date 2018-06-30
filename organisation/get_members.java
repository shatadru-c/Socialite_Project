/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package organisation;

import ngo.*;
import java.io.*;


/**
 *
 * @author Shatadru
 */
public class get_members {

    public String reg_mem(String path)
    {


        read_mem r=new read_mem();

        File f=new File(path);
        String result="";
        if(f.isDirectory())
        {
            File fr[]=f.listFiles();
            int i=0;
            while(i<fr.length)
            {
                if(fr[i].getName().contains("temp"))
                {
                 
                    int len=fr[i].getName().length();
                    String fname=(fr[i].getName().substring(5,len-4));
                    System.out.println(fname);
                    result+=r.readFile(fr[i].getPath(),fname);
                    fr[i].delete();
                    
                    
                }
                i++;
            }
        }
        return result;
    }

}
