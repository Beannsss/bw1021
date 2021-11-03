package bw1021;

import java.time.LocalDate;

import bw1021.controller.RentalCheckout;
import bw1021.enums.ToolEnum;
import bw1021.exceptions.InvalidDiscountException;
import bw1021.exceptions.InvalidRentalDaysException;

public class Main {

	public static void main(String[] args) throws InvalidDiscountException, InvalidRentalDaysException {
		RentalCheckout checkout = RentalCheckout.build(ToolEnum.JAKR, 9, 0, LocalDate.of(2015, 7, 2)).printAgreement().complete();
	}

}
