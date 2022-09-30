package com.dxc.createnewmotopolicy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	
	@Id
	@Column(name = "user_name", length = 10, nullable = false)
	private String userName;
	@Column(name = "password", length = 20, nullable = false)
	private String password;
	@Column(name = "user_role", length = 20, nullable = false)
	private String role;
	@Column(name = "active", length = 1, nullable = false)
	private boolean active;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + ", role=" + role + ", active=" + active
				+ "]";
	}
	
	
}
