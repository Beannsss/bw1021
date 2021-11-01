package bw1021.enums;

public enum ToolType {
	Ladder(1.99, true, true, false),
	Chainsaw(1.49, true, false, true),
	Jackhammer(2.99, true, false, false),
	
	;
	
	private double dailyCharge;
	private boolean weekDayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;

	ToolType(double dailyCharge, boolean weekDayCharge, boolean weekendCharge, boolean holidayCharge) {
		this.dailyCharge = dailyCharge;
		this.weekDayCharge = weekDayCharge;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
	}
	
	public double getDailyCharge() {
		return dailyCharge;
	}

	public boolean isWeekDayCharge() {
		return weekDayCharge;
	}

	public boolean isWeekendCharge() {
		return weekendCharge;
	}

	public boolean isHolidayCharge() {
		return holidayCharge;
	}
}

