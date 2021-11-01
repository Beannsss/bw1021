package bw1021;

import java.time.LocalDate;

import bw1021.enums.ToolEnum;
import bw1021.exceptions.InvalidDiscountException;
import bw1021.exceptions.InvalidRentalDaysException;
import bw1021.model.RentalAgreement;
import bw1021.model.RentalTool;

public class Main {

	public static void main(String[] args) throws InvalidDiscountException, InvalidRentalDaysException {
		RentalAgreement rentalAgreement = new RentalAgreement.Builder(ToolEnum.JAKR)
				.checkoutDate(LocalDate.of(2020, 7, 2))
				.rentalDays(4)
				.discountPercent(50)
				.build();
		
		System.out.println(rentalAgreement);
	}

}
