package journey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import Destination.destinationPrevYear;
import lib.ProcessFile;

/**
 * Class to manage the linked list
 * @author Rahul Vijayakumar
 *
 */
public class JourneyThisYearLL {
	
	LinkedList<JourneyThisYear> jTHLL;
	
	public JourneyThisYearLL ()	{
		jTHLL = new LinkedList<JourneyThisYear>();
	}
	
	/**
	 * Add element to linked list of journey.
	 * @param jTH
	 */
	public void add(JourneyThisYear jTH){
		jTHLL.add(jTH);
	}
	
	/**
	 * reads file with given name, extracting student data, creating student
	 * objects and adding them to the list. Blank lines are skipped
	 *
	 * @param filename
	 *            the name of the input file
	 *
	 */
	public JourneyThisYearLL readFile(String filename){
		JourneyThisYear journey = new JourneyThisYear();
		Integer passNo;
		ArrayList<String> inputStr;
		inputStr = ProcessFile.readFile(filename);
		for(String str: inputStr){
			String strParts[] = str.split(",");	
			if(JourneyThisYear.validateInput(strParts)){
				journey.setRegistationNo(strParts[0].trim());
				journey.setDestinationName(strParts[1].trim());
				passNo = new Integer(strParts[2].trim());
				journey.setNoOfPassengers(passNo);
			}
			this.add(journey);
		}
		
		return this;		
	}

}