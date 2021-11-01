package bw1021.model;


public abstract class Tool {
	
	protected String toolCode;
	protected String toolType;
	protected String brand;
	
	protected abstract boolean isRental();

	public String getToolCode() {
		return toolCode;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getToolType() {
		return toolType;
	}
	
}
