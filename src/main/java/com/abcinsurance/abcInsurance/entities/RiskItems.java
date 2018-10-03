package com.abcinsurance.abcInsurance.entities;

/**
 * 
 * @author Pieterv
 *
 */
public enum RiskItems {
	PrivateVehicle("Private Vechile"), CommercialVehicle("Commercial Vechile"), HouseHold("House Hold");

	private final String name;

	private RiskItems(final String n) {
		this.name = n;
	}

	public String getName() {
		return name;
	}
}
