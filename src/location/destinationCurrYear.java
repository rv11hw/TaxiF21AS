package location;

/**
 * 
 * @author jeff joshi
 *
 */

public class destinationCurrYear {
	private String dest;
	private double dist;

	public destinationCurrYear(String de, double di) {

		this.dest = de;
		this.dist = di;

		if ((dest.trim().length() == 0) || (dest == null)) {
			throw new IllegalStateException("Invalid destination and distance");
		}
	}

	public String getdest() {
		return dest;
	}

	public double getdist() {
		return dist;
	}
}
