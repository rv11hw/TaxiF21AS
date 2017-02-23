package journey;

import java.util.ArrayList;
import java.util.TreeSet;

import lib.ProcessFile;

/**
 * Class to manage the linked list
 * 
 * @author Rahul Vijayakumar
 *
 */
public class JourneyThisYearTS {

	TreeSet<JourneyThisYear> jTHLL;

	public JourneyThisYearTS() {
		jTHLL = new TreeSet<JourneyThisYear>(new JourneyComparator());
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
	public void readFile(String filename) {
		Integer passNo;
		ArrayList<String> inputStr;
		inputStr = ProcessFile.readFile(filename);
		for (String str : inputStr) {
			JourneyThisYear journey = new JourneyThisYear();
			String strParts[] = str.split(",");
			if (JourneyThisYear.validateInput(strParts)) {
				journey.setRegistationNo(strParts[0].trim());
				journey.setDestinationName(strParts[1].trim());
				passNo = new Integer(strParts[2].trim());
				journey.setNoOfPassengers(passNo);
				journey.calcCost();
			}
			jTHLL.add(journey);
		}
	}
	
	public Iterable<JourneyThisYear> getIteratable(){
		return  this.jTHLL;
	}
	
	public void dispContent(int limit){
		dispContent(limit, false);
	}
	
	public void dispContent(int limit, boolean desc){
		TreeSet<JourneyThisYear> ts;
		int count = 0;
		if(desc){
			ts = (TreeSet<JourneyThisYear>) jTHLL.descendingSet();
		}else{
			ts = jTHLL;
		}
		if(limit == 0){
			limit = ts.size();
		} 
		for(JourneyThisYear j: ts){
			count++;
			System.out.print(j.getRegistationNo()+"\t");
			System.out.print(j.getDestinationName()+"\t");
			System.out.print(j.getDistance()+" km \t");
			System.out.print(j.getNoOfPassengers()+" people \t");
			System.out.println(j.getCost()+" AED");
			if(count == limit)
				break;
		}
	}

	public TreeSet<String> getLocationByReg(String regNo) {
		TreeSet<String> destSet = new TreeSet<String>();
		return getLocationByReg(regNo, destSet);
	}
	public TreeSet<String> getLocationByReg(String regNo, TreeSet<String> destSet) {	
		System.out.println(jTHLL.size());
		for( JourneyThisYear jour : jTHLL){
			if(jour.getRegistationNo().compareTo(regNo) == 0){
				destSet.add(jour.getDestinationName());
			}
		}
		return destSet;
	}
	


}