package bw1021.exceptions;

public class InvalidRentalDaysException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3792328459007269990L;
	private int rentalDays;

	public InvalidRentalDaysException(int rentalDays) {
		this.rentalDays = rentalDays;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Invalid Rental Days - " + rentalDays + " - Rental Days must be 1 or greater");
		return sb.toString();
	}

}
