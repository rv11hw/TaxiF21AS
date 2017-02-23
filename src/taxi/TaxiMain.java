package taxi;

import java.util.*;
import Destination.*;
import driver.Driver;
import journey.JourneyThisYear;
import journey.JourneyThisYearTS;
import lib.ProcessFile;
import location.PresentYear;
import name_and_ID.SortMethod;

public class TaxiMain {

	public static void main(String[] args) {
		ProcessFile pf = new ProcessFile();
		// Creating an ArrayList of objects of the class destinationPrevYear
		ArrayList<destinationPrevYear> destPrevY = new ArrayList<destinationPrevYear>();
		// Creating a HashSet to store the unique destinations
		HashSet<String> prevYearDest = new HashSet<String>();
		// Reading file from the res folder
		ArrayList<String> destinationPrevYearStr = ProcessFile.readFile("PrevYearDest");
		for (String line : destinationPrevYearStr) {
			// For each valid line read, store the value to the object and add
			// the object to the ArrayList
			destinationPrevYear dpy = new destinationPrevYear(line);
			destPrevY.add(dpy);
		}
		// Iterating through each object, getting the destination value for the
		// current object and then storing them in the HashSet
		Iterator<destinationPrevYear> itr = destPrevY.iterator();
		while (itr.hasNext()) {
			destinationPrevYear x = (destinationPrevYear) itr.next();
			prevYearDest.add(x.getDest());
		}
		
		PresentYear locationList = new PresentYear();

		// Create Linked list of journeys this year
		JourneyThisYearTS journeyTS = new JourneyThisYearTS();
		// Read and process journeys
		journeyTS.readFile("journeysofar.csv",locationList);
		// Display content most expensive 5
		String ipTextJE = journeyTS.dispContent(5);
		ProcessFile.writeFile("Journey.txt", ipTextJE);
		// Display content least expensive 5
		String ipTextJC = journeyTS.dispContent(5,true);
		ProcessFile.writeFile("Journey.txt", ipTextJC, true);

		// get driver location tree map
		String ipTextLoc = getDriverLocation(journeyTS);
		ProcessFile.writeFile("DriverLocation.txt", ipTextLoc);
				
		// journey-year map
		String ipTextLocYear = getJourneyYear(destPrevY, journeyTS);
		ProcessFile.writeFile("LocationYear.txt", ipTextLocYear);

		System.out.println("Done!");
		
	}

	public static String getJourneyYear(ArrayList<destinationPrevYear> destPrevY, JourneyThisYearTS journeyTS) {
		final String prvYearStr = "2016";
		final String currYearStr = "2017";
		String str = new String();
		HashMap<String, String> journeyYear = new HashMap<String, String>();
		ArrayList<String> travelledBoth = new ArrayList<String>();
		ArrayList<String> travelledPrev = new ArrayList<String>();
		ArrayList<String> travelledCurr = new ArrayList<String>();
		for (destinationPrevYear prvYear : destPrevY) {
			journeyYear.put(prvYear.getDest(), prvYearStr);
		}
		for (JourneyThisYear js : journeyTS.getIteratable()) {
			if (!journeyYear.containsKey(js.getDestinationName())) {
				journeyYear.put(js.getDestinationName(), currYearStr);
			} else {
				journeyYear.put(js.getDestinationName(), "0");
			}
		}

		for (String key : journeyYear.keySet()) {
			if (journeyYear.get(key) == "2016") {
				travelledPrev.add(key);
			} else if (journeyYear.get(key) == "2017") {
				travelledCurr.add(key);
			} else {
				travelledBoth.add(key);
			}
		}

		
		str+= ("\n"+travelledPrev.size()+" Places travelled in year "+prvYearStr+"\n");
		str+= (getListStr(travelledPrev));
		str+= ("\n"+travelledCurr.size()+" Places travelled in year "+currYearStr+"\n");
		str+= (getListStr(travelledCurr));
		str+= ("\n"+travelledBoth.size()+" Places travelled during both years"+"\n");
		str+= (getListStr(travelledBoth));
		
		return str;
	
	}
	
	static String getListStr(ArrayList<String> strList){
		String retStr = new String();
		for(String str: strList){
			retStr+= str+"\n";
		}
		return retStr;
	}

	public static String getDriverLocation(JourneyThisYearTS journeyTS){
		String dName = new String();
		String str;
		Driver d = new Driver();
		SortMethod nameId = new SortMethod();
		SortMethod dNameId = nameId.readFile("driver_input.csv");
		HashMap<String, String> ds = d.getDriverSet(dNameId.getSort());
		TreeMap<String, TreeSet<String>> DriverLocation = new TreeMap<String, TreeSet<String>>();
		for (String driverReg : ds.keySet()) {
			dName = ds.get(driverReg);
			if (DriverLocation.containsKey(dName)) {
				DriverLocation.put(dName, journeyTS.getLocationByReg(driverReg, DriverLocation.get(dName)));
			} else {
				DriverLocation.put(dName, journeyTS.getLocationByReg(driverReg));
			}
		}
		
		str = (d.prepareDriverOp(DriverLocation));
		return str;
	}
}
