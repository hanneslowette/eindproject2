package org.betavzw.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adres implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String straat;
	private String huisnummer;
	private String busnummer;
	private String postcode;
	private String gemeente;

	public Adres() {
	}

	public Adres(String straat, String huisnummer, String busnummer,
			String postcode, String gemeente) {
		setStraat(straat);
		setHuisnummer(huisnummer);
		setBusnummer(busnummer);
		setPostcode(postcode);
		setGemeente(gemeente);
	}

	public int getId() {
		return id;
	}
	
	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
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
