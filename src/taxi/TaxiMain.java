package taxi;

import java.io.*;
import java.util.*;
import Destination.*;
import journey.JourneyThisYearLL;
import lib.ProcessFile;

public class TaxiMain
{

	public static void main(String[] args)
	{
		ProcessFile pf = new ProcessFile();
		//Creating an ArrayList of objects of the class destinationPrevYear
		ArrayList<destinationPrevYear> destPrevY = new ArrayList<destinationPrevYear>();
		//Creating a HashSet to store the unique destinations
		HashSet<String> prevYearDest = new HashSet<String>();
		//Create Linked list of journeys this year
		JourneyThisYearLL journeyTY = new JourneyThisYearLL();
		
		//Reading file from the res folder
		ArrayList<String>destinationPrevYearStr = ProcessFile.readFile("PrevYearDest");		
			for (String line: destinationPrevYearStr)
			{
				//For each valid line read, store the value to the object and add the object to the ArrayList				
				destinationPrevYear dpy = new destinationPrevYear(line);
				destPrevY.add(dpy);
			}
		//Iterating through each object, getting the destination value for the current object and then storing them in the HashSet
		Iterator<destinationPrevYear> itr = destPrevY.iterator();
		while(itr.hasNext())
		{
			destinationPrevYear x = (destinationPrevYear)itr.next();
			prevYearDest.add(x.getDest());
		}
		System.out.println(prevYearDest);
		
		journeyTY = journeyTY.readFile("journeysofar.csv");
		
	}
}
