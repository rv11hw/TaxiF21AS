package name_and_ID;

import java.util.List;

import journey.JourneyThisYear;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import lib.ProcessFile;
import java.util.LinkedList;

import lib.ProcessFile;

public class SortMethod
{
	LinkedList<Sort> sort;
	
	public void SortingMethod()
	{
		LinkedList<Sort> sort;
	}
	
	public void add(Sort st)
	{
		sort.add(st);
	}
	
	public SortMethod readFile(String filename)
	{
	Sort nID = new Sort();
	ArrayList<String> inputStr;
	inputStr = ProcessFile.readFile(filename);
	for(String str: inputStr)
	{
		String strParts[] = str.split(",");	
		if(JourneyThisYear.validateInput(strParts))
			{
				nID.SetRegNo(strParts[0].trim());
				nID.SetName(strParts[1].trim());
			}
		this.add(nID);
	}
	
	return this;
	}
}
