package bw1021.model;

public abstract class Tool {
	
	protected boolean isRental;
	protected String toolCode;
	
	protected abstract boolean isRental();
	
	protected String getToolCode() {
		return toolCode;
	}
}
