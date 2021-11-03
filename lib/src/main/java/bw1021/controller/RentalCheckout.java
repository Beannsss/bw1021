package bw1021.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;

import bw1021.enums.ToolEnum;
import bw1021.exceptions.InvalidDiscountException;
import bw1021.exceptions.InvalidRentalDaysException;
import bw1021.model.RentalAgreement;

public class RentalCheckout extends Checkout {
	
	private RentalCheckout(ToolEnum tool, int days, int discountPercentage, LocalDate checkoutDate) throws InvalidDiscountException, InvalidRentalDaysException {
		agreement = new RentalAgreement.Builder(tool)
				.rentalDays(days)
				.discountPercent(discountPercentage)
				.checkoutDate(checkoutDate)
				.build();
	}

	public static RentalCheckout build(ToolEnum tool, int days, int discountPercentage, LocalDate checkoutDate) throws InvalidDiscountException, InvalidRentalDaysException {
		return new RentalCheckout(tool, days, discountPercentage, checkoutDate);
	}
	
	@Override
	public RentalCheckout complete() {
		getAgreement().setCheckoutCompleteDate(LocalDateTime.now());
//		store();
		return this;
	}
	
	@Override
	public RentalCheckout printAgreement() {
		System.out.println(getAgreement());
		return this;
	}

	@Override
	public RentalAgreement getAgreement() {
		return (RentalAgreement) agreement;
	}
	
	
}
