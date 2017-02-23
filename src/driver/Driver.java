package driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import name_and_ID.Sort;

public class Driver {
	String DriverName;
	String RegNo;

	public HashMap<String, String> getDriverSet(LinkedList<Sort> nameId) {
		HashMap<String, String> dst = new HashMap<String, String>();
		for (Sort driver : nameId) {
			dst.put(driver.getRegNo(), driver.getName());
		}
		return dst;
	}

	public String prepareDriverOp(TreeMap<String, TreeSet<String>> DriverLocation) {
		String str = new String();
		Set<String> drivers = DriverLocation.keySet();
		for (String s : drivers) {
			str += s + "\n";
			str += getListStr(DriverLocation.get(s));
		}
		return str;
	}

	String getListStr(TreeSet<String> strList) {
		String retStr = new String();
		if (strList.size() == 0) {
			retStr += "  No Locations \n";
		} else {
			for (String str : strList) {
				retStr += "  " + str + "\n";
			}
		}
		return retStr;
	}
}
