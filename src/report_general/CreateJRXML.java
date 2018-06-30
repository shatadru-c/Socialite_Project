package report_general;
import java.io.*;

public class CreateJRXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String usr=args[0];
			usr=usr.toUpperCase();
			//String org="INFOSYS";
			
			String sta=args[1];
			sta=sta.toUpperCase();
			
			BufferedReader br=new BufferedReader(new FileReader("Certificate_Gen.jrxml"));
			BufferedWriter bw=new BufferedWriter(new FileWriter("USR_"+usr+".jrxml"));
			
			
			
			String tmp="";
			while((tmp=br.readLine()) != null)
			{
				if(tmp.contains("STATE"))
				{
					System.out.println(tmp);
					tmp=tmp.replaceFirst("STATE", sta);
					System.out.println(tmp);
				}
				bw.write(tmp+"\n");
			}
			bw.close();
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
