package bw1021;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bw1021.controller.RentalCheckout;
import bw1021.enums.ToolEnum;
import bw1021.exceptions.InvalidDiscountException;
import bw1021.exceptions.InvalidRentalDaysException;
import bw1021.model.RentalAgreement;

class CheckoutTest {

	@Test
	void checkout0() {
		assertThrows(InvalidRentalDaysException.class, () -> {
			RentalCheckout.build(ToolEnum.JAKR, 0, 0, LocalDate.of(2015, 9, 3));
		});
	}

	@Test
	void checkOut1() {
		assertThrows(InvalidDiscountException.class, () -> {
			RentalCheckout.build(ToolEnum.JAKR, 5, 101, LocalDate.of(2015, 9, 3));
		});
	}

	@Test
	void checkOut2() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalCheckout checkout = RentalCheckout.build(ToolEnum.LADW, 3, 10, LocalDate.of(2020, 7, 2));
		RentalAgreement rentalAgreement = checkout.getAgreement();

		assertEquals(3.58, rentalAgreement.getFinalCharge().doubleValue());
	}

	@Test
	void checkOut3() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalCheckout checkout = RentalCheckout.build(ToolEnum.CHNS, 5, 25, LocalDate.of(2015, 7, 2));
		RentalAgreement rentalAgreement = checkout.getAgreement();

		assertEquals(3.35, rentalAgreement.getFinalCharge().doubleValue());
	}

	@Test
	void checkOut4() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalCheckout checkout = RentalCheckout.build(ToolEnum.JAKD, 6, 0, LocalDate.of(2015, 9, 3));
		RentalAgreement rentalAgreement = checkout.getAgreement();

		assertEquals(8.97, rentalAgreement.getFinalCharge().doubleValue());
	}

	@Test
	void checkOut5() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalCheckout checkout = RentalCheckout.build(ToolEnum.JAKR, 9, 0, LocalDate.of(2015, 7, 2));
		RentalAgreement rentalAgreement = checkout.getAgreement();

		assertEquals(17.94, rentalAgreement.getFinalCharge().doubleValue());
	}

	@Test
	void checkOut6() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalCheckout checkout = RentalCheckout.build(ToolEnum.JAKR, 4, 50, LocalDate.of(2020, 7, 2));
		RentalAgreement rentalAgreement = checkout.getAgreement();

		assertEquals(1.49, rentalAgreement.getFinalCharge().doubleValue());
	}

}
