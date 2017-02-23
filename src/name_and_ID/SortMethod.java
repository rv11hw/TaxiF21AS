package name_and_ID;

import java.util.ArrayList;
import java.util.LinkedList;

import lib.ProcessFile;

public class SortMethod {
	LinkedList<Sort> sort;

	public SortMethod() {
		sort = new LinkedList<Sort>();
	}

	public void add(Sort st) {
		sort.addLast(st);
	}

	public SortMethod readFile(String filename) {

		ArrayList<String> inputStr;
		inputStr = ProcessFile.readFile(filename);
		for (String str : inputStr) {
			Sort nID = new Sort();
			String strParts[] = str.split(",");
			// if(JourneyThisYear.validateInput(strParts))
			// {
			nID.SetRegNo(strParts[0].trim());
			nID.SetName(strParts[1].trim());
			// }
			this.add(nID);
		}

		return this;
	}

	public LinkedList<Sort> getSort() {
		return sort;
	}
}
