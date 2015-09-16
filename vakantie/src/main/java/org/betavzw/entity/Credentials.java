package org.betavzw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Credentials {

	/**
	 * Primary key
	 */
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id private int id;
	
	/**
	 * 
	 */
	private String username;
	
	/**
	 * 
	 */
	private String password;

	public int getId() {
		return id;
	}

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

}