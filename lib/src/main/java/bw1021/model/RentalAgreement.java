package bw1021.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bw1021.enums.HolidayEnum;
import bw1021.enums.ToolEnum;
import bw1021.exceptions.InvalidDiscountException;
import bw1021.exceptions.InvalidRentalDaysException;

public class RentalAgreement extends Agreement {

	private int rentalDays;
	private int chargeDays;
	private int discountPercentDisplay;
	private LocalDateTime checkoutCompleteDate;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BigDecimal preDiscountCharge;
	private int discountPercent = 0;
	private BigDecimal discountAmount;
	private BigDecimal finalCharge;

	DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy");

	private RentalAgreement(Builder builder) {
		this.tool = builder.tool;
		this.rentalDays = builder.rentalDays;
		this.chargeDays = builder.chargeDays;
		this.checkoutDate = builder.checkoutDate;
		this.dueDate = builder.dueDate;
		this.preDiscountCharge = builder.preDiscountCharge;
		this.discountPercent = builder.discountPercent;
		this.discountAmount = builder.discountAmount;
		this.finalCharge = builder.finalCharge;
	}

	public RentalTool getTool() {
		return (RentalTool) tool;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public LocalDate getCheckOutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public BigDecimal getFinalCharge() {
		return finalCharge;
	}

	public LocalDateTime getCheckoutCompleteDate() {
		return checkoutCompleteDate;
	}

	public void setCheckoutCompleteDate(LocalDateTime checkoutCompleteDate) {
		this.checkoutCompleteDate = checkoutCompleteDate;
	}

	public int getDiscountPercentDisplay() {
		return discountPercentDisplay;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tool code: " + getTool().getToolCode() + "\n");
		sb.append("Tool type: " + getTool().getToolType() + "\n");
		sb.append("Tool brand: " + getTool().getBrand() + "\n");
		sb.append("Rental days: " + getRentalDays() + "\n");
		sb.append("Checkout date: " + getCheckOutDate().format(dateFormat) + "\n");
		sb.append("Due date: " + getDueDate().format(dateFormat) + "\n");
		sb.append("Daily rental charge: " + currencyFormat.format(getTool().getDailyCharge()) + "\n");
		sb.append("Charge days: " + getChargeDays() + "\n");
		sb.append("Pre-discount charge: " + currencyFormat.format(getPreDiscountCharge()) + "\n");
		sb.append("Discount percent: " + getDiscountPercent() + "%\n");
		sb.append("Discount amount: " + currencyFormat.format(getDiscountAmount()) + "\n");
		sb.append("Final charge: " + currencyFormat.format(getFinalCharge()) + "\n");
		return sb.toString();
	}

	public static class Builder {

		private final RentalTool tool;
		private int rentalDays;
		private int chargeDays;
		private int discountPercent;
		private LocalDate checkoutDate;
		private LocalDate dueDate;
		private BigDecimal preDiscountCharge;
		private BigDecimal discountAmount = new BigDecimal(0);
		private BigDecimal finalCharge;

		public Builder(RentalTool tool) {
			this.tool = tool;
		}

		public Builder(ToolEnum tool) {
			this.tool = new RentalTool.Builder(tool).build();
		}

		public Builder checkoutDate(LocalDate checkoutDate) {
			this.checkoutDate = checkoutDate;
			return this;
		}

		public Builder rentalDays(int rentalDays) {
			this.rentalDays = rentalDays;
			return this;
		}

		public Builder discountPercent(int discountPercent) {
			this.discountPercent = discountPercent;
			return this;
		}

		private void getAgreementDays() {
			LocalDate currentDate = checkoutDate;
			dueDate = currentDate.plusDays(rentalDays);
			int dayIncrement = 0;
			for (int i = 0; i < rentalDays; i++) {
				currentDate = currentDate.plusDays(1);
				switch (currentDate.getDayOfWeek()) {
				case MONDAY:
					if (!tool.isHolidayCharge() && dayIsHoliday(currentDate.minusDays(1)))
						break;
				case FRIDAY:
					if (!tool.isHolidayCharge() && dayIsHoliday(currentDate.plusDays(1)))
						break;
				case TUESDAY:
				case WEDNESDAY:
				case THURSDAY:
					if (tool.isWeekdayCharge() && (tool.isHolidayCharge() || !dayIsHoliday(currentDate)))
						dayIncrement++;
					break;
				case SATURDAY:
				case SUNDAY:
					if (tool.isWeekendCharge())
						dayIncrement++;
					break;
				default:
					break;

				}
			}
			chargeDays = dayIncrement;
		}

		private boolean dayIsHoliday(LocalDate currentDay) {
			for (HolidayEnum holiday : HolidayEnum.values()) {
				if (currentDay.isEqual(new Holiday.Builder(holiday).of(currentDay.getYear()).getDay()))
					return true;
			}
			return false;
		}

		private void getCost() {
			preDiscountCharge = new BigDecimal(tool.getDailyCharge() * chargeDays).setScale(2, RoundingMode.HALF_UP);
			if (discountPercent != 0)
				discountAmount = preDiscountCharge.multiply(getPercentBigDecimal(discountPercent)).setScale(2, RoundingMode.HALF_UP);
			finalCharge = preDiscountCharge.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);
		}

		public BigDecimal getPercentBigDecimal(int percent) {
			return new BigDecimal(percent).divide(new BigDecimal(100));
		}

		public RentalAgreement build() throws InvalidDiscountException, InvalidRentalDaysException {
			validate();
			getAgreementDays();
			getCost();
			RentalAgreement rentalAgreement = new RentalAgreement(this);

			return rentalAgreement;
		}

		private void validate() throws InvalidDiscountException, InvalidRentalDaysException {
			if (discountPercent < 0 || discountPercent > 100)
				throw new InvalidDiscountException(discountPercent);
			if (rentalDays < 1)
				throw new InvalidRentalDaysException(rentalDays);
		}
	}

}
