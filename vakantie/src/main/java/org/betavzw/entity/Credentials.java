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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

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
	@OneToOne
	private Werknemer werknemer;

	/**
	 * Het type van de accounts
	 */
	@SuppressWarnings("unused")
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
		if (werknemer.getTeam() != null && werknemer.getTeam().getCode() != null
				&& werknemer.getTeam().getCode().equals("HR")) {
			return AccountType.HR;
		}
		else if (werknemer.getTeam() != null && werknemer.getTeam().getTeamverantwoordelijke() != null
				&& werknemer.getTeam().getTeamverantwoordelijke().getPersoneelsNr() == werknemer.getPersoneelsNr()) {
			return AccountType.TEAMVERANTWOORDELIJKE;
		}
		return AccountType.WERKNEMER;
	}

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
	}

}