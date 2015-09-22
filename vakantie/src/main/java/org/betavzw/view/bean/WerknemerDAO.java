package org.betavzw.view.bean;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.hibernate.validator.constraints.NotEmpty;

@Named("wndao")
@RequestScoped
public class WerknemerDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Bean<Team> team_bean;

	@NotEmpty
	@NotNull
	private String voornaam;
	@NotEmpty
	@NotNull
	private String naam;
	@NotEmpty
	@NotNull
	private String email;
	@NotNull
	private Date geboortedatum;
	@NotEmpty
	@NotNull
	private String straat;
	@NotEmpty
	@NotNull
	private String huisnummer;
	private String busnummer;
	@NotEmpty
	@NotNull
	private String postcode;
	@NotEmpty
	@NotNull
	private String gemeente;
	private int teamid = 3;

	public Werknemer getEntity() throws GeboortedatumInDeToekomstException {
		Werknemer werknemer = new Werknemer();
		werknemer.setVoornaam(voornaam);
		werknemer.setNaam(naam);
		werknemer.setEmail(email);

		Adres adres = new Adres();
		adres.setStraat(straat);
		adres.setHuisnummer(huisnummer);
		if (busnummer != null || !busnummer.equals("")) {
			adres.setBusnummer(busnummer);
		}
		adres.setPostcode(postcode);
		adres.setGemeente(gemeente);
		
		werknemer.setAdres(adres);
		
		werknemer.setGeboortedatum(geboortedatum.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
		System.out.println("TEAM ID SELECTED:");
		System.out.println(teamid);
		werknemer.setTeam(team_bean.getSingle(new Filter("id", teamid)));
		
		return werknemer;
	}
	
	public void merge(Werknemer entity) throws GeboortedatumInDeToekomstException{
		voornaam = entity.getVoornaam();
		naam = entity.getNaam();
		email = entity.getEmail();
		
		Adres adres = entity.getAdres();
		
		straat = adres.getStraat();
		huisnummer = adres.getHuisnummer();
		busnummer = adres.getBusnummer();
		postcode = adres.getPostcode();
		gemeente = adres.getGemeente();
		geboortedatum = Date.from(entity.getGeboortedatum().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
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

	public int getTeamid() {
		return teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
