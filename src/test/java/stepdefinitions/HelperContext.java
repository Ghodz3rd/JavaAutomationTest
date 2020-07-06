package stepdefinitions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HelperContext {
	
	static String systemPath = System.getProperty("user.dir");
	
	static String DataFromCSV(String Param) throws IOException
    {
		
       String dataResult = "";
       String path = systemPath + "/files/data/Element.csv";
       String row = "";
       
       BufferedReader csvReader;
		try {
			csvReader = new BufferedReader(new FileReader(path));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(";");
			       
			    if(data[0].substring(0, 1) != "#"){  			    	
			    	if (data[0].equals(Param)) {
			    		dataResult = data[1];
			    		csvReader.close();
			    		break; 
			    	}
			    }
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 return dataResult;
	}
}
