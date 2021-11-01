package bw1021.enums;

public enum HolidayEnum {
	July4th("Independence Day"),
	LaborDay("Labor Day"),
	;
	
	private String niceName;

	private HolidayEnum(String niceName) {
		this.niceName = niceName;
	}

	public String getNiceName() {
		return niceName;
	}
}