package bw1021.model;

import bw1021.enums.ToolEnum;

public class RentalTool extends Tool {
	
	private double dailyCharge;
	private boolean isWeekdayCharge;
	private boolean isWeekendCharge;
	private boolean isHolidayCharge;

	private RentalTool(Builder builder) {
		this.toolCode = builder.getTool().name();
		this.toolType = builder.getTool().getType().name();
		this.brand = builder.getTool().getBrand().name();
		this.dailyCharge = builder.getTool().getType().getDailyCharge();
		this.isWeekdayCharge = builder.getTool().getType().isWeekDayCharge();
		this.isWeekendCharge = builder.getTool().getType().isWeekendCharge();
		this.isHolidayCharge = builder.getTool().getType().isHolidayCharge();
	}

	@Override
	protected boolean isRental() {
		return true;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public boolean isWeekdayCharge() {
		return isWeekdayCharge;
	}

	public boolean isWeekendCharge() {
		return isWeekendCharge;
	}

	public boolean isHolidayCharge() {
		return isHolidayCharge;
	}

	public static class Builder {
		
		private ToolEnum tool;

		public Builder(ToolEnum tool) {
			this.tool = tool;
		}

		public RentalTool build() {
			validate();
			return new RentalTool(this);
		}

		private boolean validate() {
			return true;
		}

		public ToolEnum getTool() {
			return tool;
		}

	}
}
