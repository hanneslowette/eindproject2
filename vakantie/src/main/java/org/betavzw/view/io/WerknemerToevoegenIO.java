package org.betavzw.view.io;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;

@Named
@RequestScoped
public class WerknemerToevoegenIO {
	
	private String naam;
	private String voornaam;
	private String email;
	private Date geboortedatum;
	
	private String straat;
	private String huisnummer;
	private String busnummer;
	private String postcode;
	private String gemeente;
	
	@Deprecated
	private Adres adres;
	
	@Inject
	private Bean<Team> team_bean;
	
	@Inject
	private Bean<Werknemer> werknemer_bean;
	
	
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
		// Werknemer w = new Werknemer();
		// Werknemer w4 = new Werknemer(naam, voornaam, adres, email,
		// geboortedatum);
		Adres a = new Adres(straat, huisnummer, busnummer, postcode, gemeente);
		Werknemer w = new Werknemer(naam, voornaam, email, geboortedatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), a);
		werknemer_bean.offer(w);
		// w.
		return View.HOME;
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
	
	public List<Team> getTeamnaam() {
		return team_bean.get();
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