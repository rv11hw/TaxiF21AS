package taxi;

import java.io.*;
import java.util.*;
import Destination.*;

public class TaxiMain
{

	public static void main(String[] args)
	{
		//Creating an ArrayList of objects of the class destinationPrevYear
		ArrayList<destinationPrevYear> destPrevY = new ArrayList<destinationPrevYear>();
		//Creating a HashSet to store the unique destinations
		HashSet<String> prevYearDest = new HashSet<String>();
		//Reading file from the res folder
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		File file = new File(cl.getResource("PrevYearDest").getFile());
		try (Scanner scanner = new Scanner(file))
		{

			while (scanner.hasNextLine())
			{
				//For each valid line read, store the value to the object and add the object to the ArrayList
				String line = scanner.nextLine();
				destinationPrevYear dpy = new destinationPrevYear(line);
				destPrevY.add(dpy);
			}

		}
		catch(FileNotFoundException e)
		{
			System.out.println("File is missing!");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		//Iterating through each object, getting the destination value for the current object and then storing them in the HashSet
		Iterator<destinationPrevYear> itr = destPrevY.iterator();
		while(itr.hasNext())
		{
			destinationPrevYear x = (destinationPrevYear)itr.next();
			prevYearDest.add(x.getDest());
		}
		System.out.println(prevYearDest);
	}
}
