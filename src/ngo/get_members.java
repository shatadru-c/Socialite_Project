/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ngo;

import java.io.*;

/**
 * 
 * @author Shatadru
 */
public class get_members {

	public void reg_mem(String path) {

		read_mem r = new read_mem();

		File f = new File(path);

		if (f.isDirectory()) {
			File fr[] = f.listFiles();
			int i = 0;
			while (i < fr.length) {
				if (fr[i].getName().contains("temp")) {
					int len = fr[i].getName().length();
					String fname = (fr[i].getName().substring(5, len - 4));
					r.readFile(fr[i].getPath(), fname);

				}
				i++;
			}
		}

	}

}
