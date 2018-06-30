package testing;
import java.io.*;

public class TestPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			File f=new File("../Integration/WebContent/reports");
			System.out.println(f.exists());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
