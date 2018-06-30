package organisation;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shatadru
 */
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import java.io.File;
import java.util.*;

public class FileRename implements FileRenamePolicy {

    String j = "";

    FileRename(String i) {
        j = i;
    }

    public File rename(File f) {



        String parentDir = f.getParent();

        //GET THE FILENAME OF THE FILE TO BE RENAMED
        String fname = f.getName();

        //GET THE FILE EXTENSION

        String fileExt = "";
        int i = -1;
        if ((i = fname.indexOf(".")) != -1) {

            fileExt = fname.substring(i);
            fname = fname.substring(0, i);

            //ADD TEMP TO THE XLS FILES HAVING MEMBERS
            if (fileExt.equals(".xls")) {
                fname = "temp_" + j;
            } //ADD LOGO TO THE JPG FILES BEING THE LOGO FOR THE ORGANISATION
            else {
                fname = "logo_" + j;
            }
        }




        //PIECE TOGETHER THE FILE NAME

        fname = parentDir + System.getProperty("file.separator") + fname + fileExt;

        File temp = new File(fname);

        return temp;

    }
}
