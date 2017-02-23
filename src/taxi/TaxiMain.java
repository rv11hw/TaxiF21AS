package taxi;

import java.io.*;
import java.util.*;
import Destination.*;
import driver.Driver;
import journey.JourneyThisYear;
import journey.JourneyThisYearTS;
import lib.ProcessFile;

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
		System.out.println(prevYearDest);

		// Create Linked list of journeys this year
		JourneyThisYearTS journeyTS = new JourneyThisYearTS();
		// Read and process journeys
		journeyTS.readFile("journeysofar.csv");
		// Display content most expensive 5
		journeyTS.dispContent(5, true);
		System.out.println("\n");
		// Display content least expensive 5
		journeyTS.dispContent(5);

		// journey-year map
		getJourneyYear(destPrevY, journeyTS);

		//
		String dName = new String();
		Driver d = new Driver();
		HashMap<String, String> ds = d.getDriverSet();
		TreeMap<String, TreeSet<String>> DriverLocation = new TreeMap<String, TreeSet<String>>();
		for (String driverReg : ds.keySet()) {
			dName = ds.get(driverReg);
			if (DriverLocation.containsKey(dName)) {
				DriverLocation.put(dName, journeyTS.getLocationByReg(driverReg, DriverLocation.get(dName)));
			} else {
				DriverLocation.put(dName, journeyTS.getLocationByReg(driverReg));
			}
		}
		System.out.println(DriverLocation);
	}

	public static void getJourneyYear(ArrayList<destinationPrevYear> destPrevY, JourneyThisYearTS journeyTS) {
		HashMap<String, String> journeyYear = new HashMap<String, String>();
		String travelledBoth = new String();
		String travelledPrev = new String();
		String travelledCurr = new String();
		for (destinationPrevYear prvYear : destPrevY) {
			journeyYear.put(prvYear.getDest(), "2016");
		}
		for (JourneyThisYear js : journeyTS.getIteratable()) {
			if (!journeyYear.containsKey(js.getDestinationName())) {
				journeyYear.put(js.getDestinationName(), "2017");
			} else {
				journeyYear.put(js.getDestinationName(), "0");
			}
		}

		for (String key : journeyYear.keySet()) {
			if (journeyYear.get(key) == "2016") {
				travelledPrev += key + "\n";
			} else if (journeyYear.get(key) == "2017") {
				travelledCurr += key + "\n";
			} else {
				travelledBoth += key + "\n";
			}
		}

		System.out.println("Places travelled in previous year");
		System.out.println(travelledPrev);
		System.out.println("Places travelled in current year");
		System.out.println(travelledCurr);
		System.out.println("Places travelled in both year");
		System.out.println(travelledBoth);
	}
}
