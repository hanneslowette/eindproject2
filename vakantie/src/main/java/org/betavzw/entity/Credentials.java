package org.betavzw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.betavzw.util.AccountType;

@Entity
public class Credentials {

	/**
	 * Primary key
	 */
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id private int id;
	
	/**
	 * De gebruikersnaam van de gebruiker
	 */
	private String username;
	
	/**
	 * Het wachtwoord van de gebruiker
	 */
	private String password;
	
	/**
	 * De werknemer voor deze credentials
	 */
	@OneToOne private Werknemer werknemer;
	
	/**
	 * Het type van de accounts
	 */
	private AccountType type;

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

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
	}

}