package com.dxc.createnewmotopolicy.model;

public class MotoPolicyInfo {
	private String policyNumber;
	private String inceptionDate;
	private String expiryDate;
	private String policyOwner;
	private String engineNumber;
	private String chassisNumber;
	private String vehicleRegisNumber;
	private String billingCurrency;
	private String sumInsured;
	private String rate;
	private String annualPre;
	private String postedPre;
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getInceptionDate() {
		return inceptionDate;
	}
	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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
	public String getBillingCurrency() {
		return billingCurrency;
	}
	public void setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
	}
	public String getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getAnnualPre() {
		return annualPre;
	}
	public void setAnnualPre(String annualPre) {
		this.annualPre = annualPre;
	}
	public String getPostedPre() {
		return postedPre;
	}
	public void setPostedPre(String postedPre) {
		this.postedPre = postedPre;
	}
	@Override
	public String toString() {
		return "MotoPolicyInfo [policyNumber=" + policyNumber + ", inceptionDate=" + inceptionDate + ", expiryDate="
				+ expiryDate + ", policyOwner=" + policyOwner + ", engineNumber=" + engineNumber + ", chassisNumber="
				+ chassisNumber + ", vehicleRegisNumber=" + vehicleRegisNumber + ", billingCurrency=" + billingCurrency
				+ ", sumInsured=" + sumInsured + ", rate=" + rate + ", annualPre=" + annualPre + ", postedPre="
				+ postedPre + "]";
	}
	
	
}
