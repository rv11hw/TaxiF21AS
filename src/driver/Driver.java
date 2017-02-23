package driver;

import java.util.HashMap;
import java.util.Set;

public class Driver {
	String DriverName;
	String RegNo;
	
	public HashMap<String,String> getDriverSet(){
		HashMap<String,String> dst = new HashMap<String,String>();
		dst.put("UT768", "A");
		dst.put("UT778", "B");
		dst.put("UT758", "C");
		dst.put("UT758", "C");
		dst.put("UT778", "B");
		dst.put("UT768", "A");
		return dst;
	}
}
