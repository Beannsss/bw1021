package bw1021.controller;

import bw1021.model.Agreement;

public abstract class Checkout {
	
	protected Agreement agreement;
	
	protected Agreement getAgreement() {
		return agreement;
	}
	
	protected abstract Checkout complete();
	
	protected abstract Checkout printAgreement();

}
