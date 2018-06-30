package maketablename;

public class MakeTableName {
	public static String makeName(String name)
	{
		String arr[]={"A","D","G","K","N","P","S","W"};
		
        String temp = (name.substring(0, 1)).toUpperCase();
        String sec = (name.substring(1, 2)).toUpperCase();
        int j=0,fl=0;
        while(sec.compareTo(arr[j])>=0)
        {
            
        	if(sec.compareTo(arr[j])==0)
        	{
        		sec=arr[j];
        		fl=1;
        		break;
        	}
        	
        	if(j==7)
        	{
        		sec="W";
        		fl=1;
        		break;
        	}
        	j++;
        }
        
        if(fl==0){
        	sec=arr[j-1];
        }
		return temp+sec;
	}
}
