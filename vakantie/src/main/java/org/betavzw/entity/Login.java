package org.betavzw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {

	private String username;
	private String password;

	@Id
	private int werknemerId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getWerknemerId() {
		return werknemerId;
	}

	public void setWerknemerId(int werknemerId) {
		this.werknemerId = werknemerId;
	}

}
