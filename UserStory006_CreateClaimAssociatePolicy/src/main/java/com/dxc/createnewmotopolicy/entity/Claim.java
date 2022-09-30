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
@Table(name = "Claim")
public class Claim implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "claim_number", updatable = false, length = 8, nullable = false)
	private String claimNumber;
	@Column(name = "policy_number", updatable = false, length = 8, nullable = false)
	private String policyNumber;
	@Temporal(TemporalType.DATE)
	private Date occurredDate;
	@Column(name = "policy_owner", length = 8, nullable = false)
	private String policyOwner;
	@Column(name = "engine_number", length = 30, nullable = false)
	private String engineNumber;
	@Column(name = "chassis_number", length = 30, nullable = false)
	private String chassisNumber;
	@Column(name = "vehicle_registration_number", length = 30, nullable = false)
	private String vehicleRegisNumber;
	@Column(name = "sum_insured", nullable = false)
	private double sumInsured;
	@Column(name = "reserve_currency", length = 30, nullable = false)
	private String reserveCurrency;
	@Column(name = "reserve_amount", nullable = false)
	private double reserveAmount;
	@Column(name = "status", length = 8, nullable = false)
	private String status;
	
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
	public Date getOccurredDate() {
		return occurredDate;
	}
	public void setOccurredDate(Date occurredDate) {
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
	public double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getReserveCurrency() {
		return reserveCurrency;
	}
	public void setReserveCurrency(String reserveCurrency) {
		this.reserveCurrency = reserveCurrency;
	}
	public double getReserveAmount() {
		return reserveAmount;
	}
	public void setReserveAmount(double reserveAmount) {
		this.reserveAmount = reserveAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Claim [claimNumber=" + claimNumber + ", policyNumber=" + policyNumber + ", occurredDate=" + occurredDate
				+ ", policyOwner=" + policyOwner + ", engineNumber=" + engineNumber + ", chassisNumber=" + chassisNumber
				+ ", vehicleRegisNumber=" + vehicleRegisNumber + ", sumInsured=" + sumInsured + ", reserveCurrency="
				+ reserveCurrency + ", reserveAmount=" + reserveAmount + ", status=" + status + "]";
	}
	public Claim() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Claim(String policyNumber, Date occurredDate, String policyOwner, String engineNumber, String chassisNumber,
			String vehicleRegisNumber, double sumInsured, String reserveCurrency, double reserveAmount, String status) {
		super();
		this.policyNumber = policyNumber;
		this.occurredDate = occurredDate;
		this.policyOwner = policyOwner;
		this.engineNumber = engineNumber;
		this.chassisNumber = chassisNumber;
		this.vehicleRegisNumber = vehicleRegisNumber;
		this.sumInsured = sumInsured;
		this.reserveCurrency = reserveCurrency;
		this.reserveAmount = reserveAmount;
		this.status = status;
	}
	
	
	
}
