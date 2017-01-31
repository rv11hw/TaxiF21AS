/**
 * Package to maintain
 */
package person;


public class Name {
	private String firstName;
	private String middleName;
	private String lastName;	
	
	/**
	 * Constructor for Name
	 */
	public Name(){
		firstName = "";
		middleName = "";
		lastName = "";
	}
	
	/**
	 * Constructor for Name
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName){
		this.firstName = firstName;
		middleName = "";
		this.lastName = lastName;
	}
	
	/**
	 * Constructor for Name
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public Name(String firstName, String middleName, String lastName){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}	
	
	/**
	 * Returns full name 
	 * 
	 * @return Full name
	 */
	public String getFullName(){
		String fullName = "";
		fullName = firstName+" "+middleName+" "+lastName;
		return fullName;
	}
	
	/**
	 * Get initials from name
	 * First letter from each part of name
	 * @return nameInitials 
	 */
	public String getInitials(){
		String nameInitials = "";
		if(firstName.length() > 1){
			nameInitials = firstName.substring(0, 1);
		}
		if(middleName.length() > 1){
			nameInitials += middleName.substring(0, 1);
		}
		if(lastName.length() > 1){
			nameInitials += lastName.substring(0, 1);
		}
		return nameInitials;
	}
	
	/**
	 * Get First Name of the person
	 * @return
	 */
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Splits the name into an array by space.
	 * If the result is 1 name, only first name will be initialized
	 * If the result is 2 names, first name and last name will be assigned
	 * If there are more than 2, first and second values are fetched
	 * as first name and middle name. The remaining is fed as last name.
	 * @param nameStr
	 * @return array with 3 values.
	 */
	public static String[] splitNameBySpace(String nameStr){
		String parts [] = nameStr.split(" ");
		int l;
		String nameArray[] = new String[3];
		if(parts.length == 1){
			nameArray[0] = parts[0];			
			nameArray[1] = "";
			nameArray[2] = "";
		} else if (parts.length == 2){
			nameArray[0] = parts[0];
			nameArray[1] = "";
			nameArray[2] = parts[1];
		} else if(parts.length >= 3){
			nameArray[0] = parts[0];
			nameArray[1] = parts[1];
			l = 2;
			for(int i=l; i<parts.length; i++){
				nameArray[2] = parts[i]+" ";
			}
		}
		return nameArray;
	}
}
