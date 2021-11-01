package bw1021.enums;

public enum ToolEnum {
	
	LADW(ToolType.Ladder, BrandEnum.Werner),
	CHNS(ToolType.Chainsaw, BrandEnum.Stihl),
	JAKR(ToolType.Jackhammer, BrandEnum.Ridgid),
	JAKD(ToolType.Jackhammer, BrandEnum.Dewalt)
	;
	
	private ToolType type;
	private BrandEnum brand;

	private ToolEnum(ToolType type, BrandEnum brand) {
		this.type = type;
		this.brand = brand;
	}

	public ToolType getType() {
		return type;
	}

	public BrandEnum getBrand() {
		return brand;
	}
}
