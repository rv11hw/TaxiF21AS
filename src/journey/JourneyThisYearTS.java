package journey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import lib.ProcessFile;
import location.PresentYear;

/**
 * Class to manage the linked list
 * 
 * @author Rahul Vijayakumar
 *
 */
public class JourneyThisYearTS {

	ArrayList<JourneyThisYear> jTHLL;

	public JourneyThisYearTS() {
		jTHLL = new ArrayList<JourneyThisYear>();
	}

	/**
	 * Add element to linked list of journey.
	 * 
	 * @param jTH
	 */
	public void add(JourneyThisYear jTH) {
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
	public void readFile(String filename, PresentYear location) {
		Integer passNo;
		ArrayList<String> inputStr;
		inputStr = ProcessFile.readFile(filename);
		for (String str : inputStr) {
			JourneyThisYear journey = new JourneyThisYear();
			String strParts[] = str.split(",");
			try{
				if (JourneyThisYear.validateInput(strParts)) {
					journey.setRegistationNo(strParts[0].trim());
					journey.setDestinationName(strParts[1].trim());
					passNo = new Integer(strParts[2].trim());
					journey.setNoOfPassengers(passNo);				
					double distance = location.getDist(journey.getDestinationName());
					journey.setDistance(distance);
					journey.calcCost();
				}
				jTHLL.add(journey);
			} catch(pasException e){
				//System.out.println("Error");
				//exit(1);
			}
			
		}
	}
	
	public Iterable<JourneyThisYear> getIteratable(){
		return  this.jTHLL;
	}
	
	public String dispContent(int limit){
		Collections.sort(jTHLL, new JourneyComparator());
		return dispContent(limit, false);
	}
	
	public String dispContent(int limit, boolean desc){
		TreeSet<JourneyThisYear> ts;
		String str = new String();
		int init = 0;
		if(limit == 0){
			limit = jTHLL.size();
		} 
		str = "\nCHARGES FOR THE TOP 5 JOURNEYS\n";
		if(desc){
			init = ((jTHLL.size() - limit)) < 0 ? 0 :((jTHLL.size() - limit));
			limit = jTHLL.size();
			str = "\nCHARGES FOR THE CHEAPEST 5 JOURNEYS\n";
		}
		
		while(init != limit){	
			JourneyThisYear j=jTHLL.get(init);
			str+= (j.getRegistationNo()+"\t");
			str+= (j.getDestinationName()+"\t");
			str+= (j.getDistance()+" km \t");
			str+= (j.getNoOfPassengers()+" people \t");
			str+= (j.getCost()+" AED");
			str+= "\n";
			init++;
		}
		return str;
	}

	/**
	 * Add location went by the car registration number
	 * @param regNo
	 * @return
	 */
	public TreeSet<String> getLocationByReg(String regNo) {
		TreeSet<String> destSet = new TreeSet<String>();
		return getLocationByReg(regNo, destSet);
	}
	
	public TreeSet<String> getLocationByReg(String regNo, TreeSet<String> destSet) {		
		for( JourneyThisYear jour : jTHLL){
			if(jour.getRegistationNo().compareTo(regNo) == 0){
				destSet.add(jour.getDestinationName());
			}
		}
		return destSet;
	}	
}