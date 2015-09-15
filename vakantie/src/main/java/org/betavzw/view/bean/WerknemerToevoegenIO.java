package org.betavzw.view.bean;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.ejb.ITeam;
import org.betavzw.ejb.IWerknemer;
import org.betavzw.ejb.WerknemerEJB;
//import org.betavzw.ejb.TeamEJB;
import org.betavzw.entities.Adres;
import org.betavzw.entities.Team;
//import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;

@Named
@RequestScoped
public class WerknemerToevoegenIO {
	private String naam;
	private String voornaam;
	private Adres adres;
	private String email;
	private Date geboortedatum;
	
	private String straat;
	private String huisnummer;
	private String busnummer;
	private String postcode;
	private String gemeente;
	
	@Inject
	private ITeam teamEJB;
	
	@EJB
	private WerknemerEJB p;
	
	
	public WerknemerToevoegenIO() {
		super();
	}


	public WerknemerToevoegenIO  (String naam, String voornaam, Adres adres,
			String email, Date geboortedatum) {
		super();
		this.naam = naam;
		this.voornaam = voornaam;
		this.adres = adres;
		this.email = email;
		this.geboortedatum = geboortedatum;
	}
	
	
	public String voegWerknemerToe() {
		//Werknemer w = new Werknemer();
		//Werknemer w4 = new Werknemer(naam, voornaam, adres, email, geboortedatum);
		Adres a = new Adres(straat, huisnummer, busnummer, postcode, gemeente);
		Werknemer w = new Werknemer(naam, voornaam, email, geboortedatum, a);
		p.toevoegen(w);
		//w.
		return "home";
		}
	
//	@EJB
//	private TeamEJB team;
//	
//	public String voegTeamToe() {
//		
//		Team t = new Team();
//		t.setNaam(naam);
//		team.aanmaken(t);
//		
//		return "home";
//	}
	
	public List<String> getTeamnaam() {
		return teamEJB.getTeams().stream().map(t -> t.getNaam()).collect(Collectors.toList());
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
