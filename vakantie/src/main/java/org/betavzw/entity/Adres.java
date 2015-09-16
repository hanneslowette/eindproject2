package org.betavzw.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Adres implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	private String Straat;
	private String huisnummer;
	private String busnummer;
	private String postcode;
	private String gemeente;
	
	
	
	
	public Adres() {
		super();
	}
	public Adres(String straat, String huisnummer, String busnummer,
			String postcode, String gemeente) {
		super();
		Straat = straat;
		this.huisnummer = huisnummer;
		this.busnummer = busnummer;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}
	public String getStraat() {
		return Straat;
	}
	public void setStraat(String straat) {
		Straat = straat;
	}
	public String getHuisnummer() {
		return huisnummer;
	}
	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}
	public String getBusnummer() {
		return busnummer;
	}
	public void setBusnummer(String busnummer) {
		this.busnummer = busnummer;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getGemeente() {
		return gemeente;
	}
	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	
	

}
