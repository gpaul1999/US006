package com.dxc.createnewmotopolicy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Clients")
public class Client {
	@Id
	@Column(name = "client_number", length = 10, nullable = false)
	private String clientNumber;

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Override
	public String toString() {
		return "Client [clientNumber=" + clientNumber + "]";
	}


}
