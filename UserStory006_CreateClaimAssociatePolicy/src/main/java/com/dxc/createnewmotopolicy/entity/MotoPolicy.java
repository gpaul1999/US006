package com.dxc.createnewmotopolicy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "MotoPolicy")
public class MotoPolicy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "policy_number", updatable = false, length = 8, nullable = false)
	private String policyNumber;
	@Temporal(TemporalType.DATE)
	private Date inceptionDate;
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	@Column(name = "policy_owner", length = 8, nullable = false)
	private String policyOwner;
	@Column(name = "engine_number", length = 30, nullable = false)
	private String engineNumber;
	@Column(name = "chassis_number", length = 30, nullable = false)
	private String chassisNumber;
	@Column(name = "vehicle_registration_number", length = 30, nullable = false)
	private String vehicleRegisNumber;
	@Column(name = "billing_currency", length = 30, nullable = false)
	private String billingCurrency;
	@Column(name = "sum_insured", nullable = false)
	private double sumInsured;
	@Column(name = "rate", nullable = false)
	private double rate;
	@Column(name = "annual_premium", nullable = false)
	private double annualPremium;
	@Column(name = "posted_premium", nullable = false)
	private double postedPremium;
	@Column(name = "status", length = 2, nullable = false)
	private String status;
	
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public Date getInceptionDate() {
		return inceptionDate;
	}
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
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
	public double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAnnualPremium() {
		return annualPremium;
	}
	public void setAnnualPremium(double annualPremium) {
		this.annualPremium = annualPremium;
	}
	public double getPostedPremium() {
		return postedPremium;
	}
	public void setPostedPremium(double postedPremium) {
		this.postedPremium = postedPremium;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public MotoPolicy() {
	}
	public MotoPolicy(Date inceptionDate, Date expiryDate, String policyOwner, String engineNumber,
			String chassisNumber, String vehicleRegisNumber, String billingCurrency, double sumInsured, double rate,
			double annualPremium, double postedPremium, String status) {
		super();
		this.inceptionDate = inceptionDate;
		this.expiryDate = expiryDate;
		this.policyOwner = policyOwner;
		this.engineNumber = engineNumber;
		this.chassisNumber = chassisNumber;
		this.vehicleRegisNumber = vehicleRegisNumber;
		this.billingCurrency = billingCurrency;
		this.sumInsured = sumInsured;
		this.rate = rate;
		this.annualPremium = annualPremium;
		this.postedPremium = postedPremium;
		this.status = status;
	}
	@Override
	public String toString() {
		return "MotoPolicy [policyNumber=" + policyNumber + ", inceptionDate=" + inceptionDate + ", expiryDate="
				+ expiryDate + ", policyOwner=" + policyOwner + ", engineNumber=" + engineNumber + ", chassisNumber="
				+ chassisNumber + ", vehicleRegisNumber=" + vehicleRegisNumber + ", billingCurrency=" + billingCurrency
				+ ", sumInsured=" + sumInsured + ", rate=" + rate + ", annualPremium=" + annualPremium
				+ ", postedPremium=" + postedPremium + ", status=" + status + "]";
	}
	
	
	
}
