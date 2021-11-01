package bw1021.exceptions;

import java.math.BigDecimal;

public class InvalidDiscountException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1746510738834168055L;
	private BigDecimal discountPercent;
	
	public InvalidDiscountException(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
		
	}
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Invalid Discount Percentage - " + discountPercent + "% - Percentage must be in the range 0-100");
		return sb.toString();
	}

}
