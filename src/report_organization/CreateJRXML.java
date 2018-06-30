package report_organization;
import java.io.*;

public class CreateJRXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String org=args[0];
			org=org.toUpperCase();
			//String org="INFOSYS";
			
			BufferedReader br=new BufferedReader(new FileReader("Sample.jrxml"));
			BufferedWriter bw=new BufferedWriter(new FileWriter("ORG_"+org+".jrxml"));
			
			
			
			String tmp="";
			while((tmp=br.readLine()) != null)
			{
				if(tmp.contains("COMPANY"))
				{
					System.out.println(tmp);
					tmp=tmp.replaceFirst("COMPANY", org);
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
