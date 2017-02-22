package lib;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import journey.JourneyThisYear;
import journey.JourneyThisYearLL;

public class ProcessFile {

	public static ArrayList<String> readFile(String filename) {
		ArrayList<String> str= new ArrayList<String>();
		try {
			File f = new File("res/"+filename);			
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				// read first line and process it
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {// ignored if blank line
					str.add(inputLine);
				}				
			}
		}
		// if the file is not found, stop with system exit
		catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		}
		return str;		
	}
}
