package bw1021;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bw1021.enums.ToolEnum;
import bw1021.exceptions.InvalidDiscountException;
import bw1021.exceptions.InvalidRentalDaysException;
import bw1021.model.RentalAgreement;
import bw1021.model.RentalTool;

class CheckoutTest {
	
	@Test void checkout0() {
		RentalTool tool = new RentalTool.Builder(ToolEnum.JAKR).build();
		assertThrows(InvalidRentalDaysException.class, () -> {
			new RentalAgreement.Builder(tool)
					.checkoutDate(LocalDate.of(2015, 9, 3))
					.rentalDays(0)
					.discountPercent(0)
					.build();
		});
	}
	
	@Test void checkOut1() {
		RentalTool tool = new RentalTool.Builder(ToolEnum.JAKR).build();
		assertThrows(InvalidDiscountException.class, () -> {
			new RentalAgreement.Builder(tool)
					.checkoutDate(LocalDate.of(2015, 9, 3))
					.rentalDays(5)
					.discountPercent(101)
					.build();
		});
	}
	
	@Test void checkOut2() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalTool tool = new RentalTool.Builder(ToolEnum.LADW).build();
			RentalAgreement rentalAgreement = new RentalAgreement.Builder(tool)
					.checkoutDate(LocalDate.of(2020, 7, 2))
					.rentalDays(3)
					.discountPercent(10)
					.build();
			
			assertEquals(3.58, rentalAgreement.getFinalCharge().doubleValue());
	}
	
	@Test void checkOut3() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalTool tool = new RentalTool.Builder(ToolEnum.CHNS).build();
			RentalAgreement rentalAgreement = new RentalAgreement.Builder(tool)
					.checkoutDate(LocalDate.of(2015, 7, 2))
					.rentalDays(5)
					.discountPercent(25)
					.build();
			
			assertEquals(3.35, rentalAgreement.getFinalCharge().doubleValue());
	}
	
	@Test void checkOut4() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalTool tool = new RentalTool.Builder(ToolEnum.JAKD).build();
			RentalAgreement rentalAgreement = new RentalAgreement.Builder(tool)
					.checkoutDate(LocalDate.of(2015, 9, 3))
					.rentalDays(6)
					.discountPercent(0)
					.build();
			
			 assertEquals(8.97, rentalAgreement.getFinalCharge().doubleValue());
	}
	
	@Test void checkOut5() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalTool tool = new RentalTool.Builder(ToolEnum.JAKR).build();
			RentalAgreement rentalAgreement = new RentalAgreement.Builder(tool)
					.checkoutDate(LocalDate.of(2015, 7, 2))
					.rentalDays(9)
					.discountPercent(0)
					.build();
			
			 assertEquals(17.94, rentalAgreement.getFinalCharge().doubleValue());
	}
	
	@Test void checkOut6() throws InvalidDiscountException, InvalidRentalDaysException {
		RentalTool tool = new RentalTool.Builder(ToolEnum.JAKR).build();
			RentalAgreement rentalAgreement = new RentalAgreement.Builder(tool)
					.checkoutDate(LocalDate.of(2020, 7, 2))
					.rentalDays(4)
					.discountPercent(50)
					.build();
			
			 assertEquals(1.49, rentalAgreement.getFinalCharge().doubleValue());
	}
	
	
	

}
