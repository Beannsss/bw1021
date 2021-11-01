package bw1021.model;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import bw1021.enums.HolidayEnum;

public class Holiday {
	
	private LocalDate day;
	
	private Holiday(Builder builder) {
		this.day = builder.getDay();
	}
	
	public LocalDate getDay() {
		return day;
	}

	public static class Builder {
		
		private HolidayEnum holiday;
		private LocalDate day;

		public Builder(HolidayEnum holiday) {
			this.holiday = holiday;
		}
		
		public LocalDate getDay() {
			return day;
		}

		public Holiday of(int year) {
			switch(holiday) {
			case July4th:
				day = LocalDate.of(year, Month.JULY, 4);
				break;
			case LaborDay:
				day = LocalDate.of(year, Month.SEPTEMBER, 1).with(firstInMonth(DayOfWeek.MONDAY));
				break;
			default:
				break;
			}
			return new Holiday(this);
		}
	}
	
}
