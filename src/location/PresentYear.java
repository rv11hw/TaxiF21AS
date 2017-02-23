package location;

import java.io.*;
import java.util.*;
import Destination.*;
import java.util.*;

public class PresentYear {
	ArrayList<destinationCurrYear> presdes = new ArrayList<destinationCurrYear>();
	HashMap<String,Double> presentyear = new HashMap<String,Double>();

	public PresentYear() {
		BufferedReader br = null;
		String data[] = new String[3];
		try {
			br = new BufferedReader(new FileReader("res/Currentyeardet.txt"));
			String inputline = br.readLine();

			while ((inputline) != null) {
				data = inputline.split(",");

				int dist = Integer.parseInt(data[1].trim());

				destinationCurrYear n = new destinationCurrYear(data[0], dist);

				presdes.add(n);

				inputline = br.readLine();
				//System.out.println(inputline);

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (NumberFormatException nfe) {

			System.out.println(data[0] + data[1]);
			System.exit(1);
		}

		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				br.close();
			} catch (IOException ioe) {
			}

			catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		Iterator<destinationCurrYear> i = presdes.iterator();
		while (i.hasNext()) {
			destinationCurrYear x = (destinationCurrYear) i.next();
			presentyear.put(x.getdest(),x.getdist());
		}
	}
	
	public HashMap<String,Double> getPresentYear(){
		return presentyear;
	}
	
	public Double getDist(String location){
		return presentyear.get(location);
	}
}
