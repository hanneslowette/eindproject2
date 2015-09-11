package org.betavzw.view.bean;

import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.entities.Adres;
import org.betavzw.entities.Werknemer;

@Named
@RequestScoped
public class WerknemerToevoegenIO {
	private String naam;
	private String voornaam;
	private Adres adres;
	private String email;
	private Date geboortedatum;
	
	
	public WerknemerToevoegenIO(String naam, String voornaam, Adres adres,
			String email, Date geboortedatum) {
		super();
		this.naam = naam;
		this.voornaam = voornaam;
		this.adres = adres;
		this.email = email;
		this.geboortedatum = geboortedatum;
	}
	
	
	public String voegWerknemerToe() {
		Werknemer w = new Werknemer();
		
		//w.
		return "home";
		}
	
	
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getGeboortedatum() {
		return geboortedatum;
	}
	public void setGeboortedatum(Date geboortedatum) {
		this.geboortedatum = geboortedatum;
	}	
	
	
	
	
	
}
