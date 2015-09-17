package org.betavzw.view.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.entity.Werknemer;
import org.betavzw.util.AccountType;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * De versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Het type van account
	 */
	private AccountType type;

	/**
	 * De aangemelde werknemer
	 */
	private Werknemer werknemer;
	
	/**
	 * Laat zien of de gebruiker is aangemeld of niet
	 */
	private boolean aangemeld;

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public boolean isAangemeld() {
		return aangemeld;
	}

	public void setAangemeld(boolean aangemeld) {
		this.aangemeld = aangemeld;
	}

}