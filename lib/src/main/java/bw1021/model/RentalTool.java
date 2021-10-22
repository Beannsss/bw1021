package bw1021.model;

public class RentalTool extends Tool {

	private double dailyCharge;
	private boolean isWeekdayCharge;
	private boolean isWeekendCharge;
	private boolean isHolidayCharge;

	private RentalTool(RentalToolBuilder builder) {
		this.toolCode = builder.toolCode;
		this.dailyCharge = builder.dailyCharge;
		this.isWeekdayCharge = builder.isWeekdayCharge;
		this.isWeekendCharge = builder.isWeekendCharge;
		this.isHolidayCharge = builder.isHolidayCharge;
	}

	@Override
	protected boolean isRental() {
		return true;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public boolean isWeekdayCharge() {
		return isWeekdayCharge;
	}

	public void setWeekdayCharge(boolean isWeekdayCharge) {
		this.isWeekdayCharge = isWeekdayCharge;
	}

	public boolean isWeekendCharge() {
		return isWeekendCharge;
	}

	public void setWeekendCharge(boolean isWeekendCharge) {
		this.isWeekendCharge = isWeekendCharge;
	}

	public boolean isHolidayCharge() {
		return isHolidayCharge;
	}

	public void setHolidayCharge(boolean isHolidayCharge) {
		this.isHolidayCharge = isHolidayCharge;
	}

	public static class RentalToolBuilder {
		private final String toolCode;
		private double dailyCharge;
		private boolean isWeekdayCharge = false;
		private boolean isWeekendCharge = false;
		private boolean isHolidayCharge = false;

		public RentalToolBuilder(String toolCode) {
			this.toolCode = toolCode;
		}

		public RentalToolBuilder dailyCharge(double dailyCharge) {
			this.dailyCharge = dailyCharge;
			return this;
		}

		public RentalToolBuilder weekDayCharge() {
			this.isWeekdayCharge = true;
			return this;
		}

		public RentalToolBuilder weekendChargee() {
			this.isWeekendCharge = true;
			return this;
		}

		public RentalToolBuilder holidayCharge() {
			this.isHolidayCharge = true;
			return this;
		}

		public RentalTool build() {
			validate();
			return new RentalTool(this);
		}

		private boolean validate() {
			return true;
		}

	}
}
