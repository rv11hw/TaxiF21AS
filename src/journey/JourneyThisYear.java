package journey;

import java.util.Comparator;

/**
 * Class to manage Journey of current year
 * @author Rahul Vijayakumar
 *
 */
public class JourneyThisYear{
	String registrationNo;
	String destinationName;	
	double distance = 1.0;
	double cost;	
	int noOfPassengers;
	
	/**
	 * Getter for registration number
	 * @return
	 */
	public String getRegistationNo() {
		return registrationNo;
	}
	/**
	 * Setter for registration number
	 * @return registration number
	 */
	public void setRegistationNo(String registationNo) {
		this.registrationNo = registationNo;
	}
	/**
	 * Getter for Destination name
	 * @return
	 */
	public String getDestinationName() {
		return destinationName;
	}
	/**
	 * Setter for Destination name
	 * @return
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	/**
	 * Getter for number of passengers
	 * @return
	 */
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	/**
	 * Setter for number of passengers
	 * @return
	 */
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	
	
	public static boolean validateInput(String[] str){
		Integer noOfPass;
		try{
			noOfPass = new Integer(str[2].trim());
		} catch(NumberFormatException n){
			System.out.println("Invalid number format");
			return false;
		}		
		return true;
	}
	
	public void calcCost(){
		double totalCost = 0.0;
		totalCost = (this.getDistance()*0.5) + (this.getNoOfPassengers()*2);
		this.setCost(totalCost);
	}

}
