package com.dxc.createnewmotopolicy.model;

public class ClaimInfo {
	private String claimNumber;
	private String policyNumber;
	private String occurredDate;
	private String policyOwner;
	private String engineNumber;
	private String chassisNumber;
	private String vehicleRegisNumber;
	private String sumInsured;
	private String reserveCurrency;
	private String reserveAmount;
	
	
	
	public String getClaimNumber() {
		return claimNumber;
	}



	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}



	public String getPolicyNumber() {
		return policyNumber;
	}



	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}



	public String getOccurredDate() {
		return occurredDate;
	}



	public void setOccurredDate(String occurredDate) {
		this.occurredDate = occurredDate;
	}



	public String getPolicyOwner() {
		return policyOwner;
	}



	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}



	public String getEngineNumber() {
		return engineNumber;
	}



	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}



	public String getChassisNumber() {
		return chassisNumber;
	}



	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}



	public String getVehicleRegisNumber() {
		return vehicleRegisNumber;
	}



	public void setVehicleRegisNumber(String vehicleRegisNumber) {
		this.vehicleRegisNumber = vehicleRegisNumber;
	}



	public String getSumInsured() {
		return sumInsured;
	}



	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}



	public String getReserveCurrency() {
		return reserveCurrency;
	}



	public void setReserveCurrency(String reserveCurrency) {
		this.reserveCurrency = reserveCurrency;
	}



	public String getReserveAmount() {
		return reserveAmount;
	}



	public void setReserveAmount(String reserveAmount) {
		this.reserveAmount = reserveAmount;
	}



	@Override
	public String toString() {
		return "ClaimInfo [claimNumber=" + claimNumber + ", policyNumber=" + policyNumber + ", occuredDate="
				+ occurredDate + ", policyOwner=" + policyOwner + ", engineNumber=" + engineNumber + ", chassisNumber="
				+ chassisNumber + ", vehicleRegisNumber=" + vehicleRegisNumber + ", sumInsured=" + sumInsured
				+ ", reserveCurrency=" + reserveCurrency + ", reserveAmount=" + reserveAmount + "]";
	}
	
	
}
