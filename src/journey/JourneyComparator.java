package journey;

import java.util.Comparator;

public class JourneyComparator implements Comparator<JourneyThisYear> {
	public int compare(JourneyThisYear s1, JourneyThisYear s2) {
		return (int)(s1.getCost() - s2.getCost());
	}
}
