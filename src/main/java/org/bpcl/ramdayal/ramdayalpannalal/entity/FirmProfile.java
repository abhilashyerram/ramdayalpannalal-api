package org.bpcl.ramdayal.ramdayalpannalal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FirmProfile {

	/**
	 * 
	 */
	@Id
	private int firmId;
	private String firmName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String district;
	private String state;
	private String website;
	private String supplyLocation;
	private String displayName;

	public int getFirmId() {
		return firmId;
	}

	public void setFirmId(int firmId) {
		this.firmId = firmId;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSupplyLocation() {
		return supplyLocation;
	}

	public void setSupplyLocation(String supplyLocation) {
		this.supplyLocation = supplyLocation;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
