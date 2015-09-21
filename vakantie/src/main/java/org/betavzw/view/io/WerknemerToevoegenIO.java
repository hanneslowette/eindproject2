package org.betavzw.view.io;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Credentials;
import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;
import org.hibernate.validator.constraints.NotEmpty;

@Named
@RequestScoped
public class WerknemerToevoegenIO {

	@NotEmpty(message = "voornaam mag niet leeg zijn")
	private String voornaam;
	@NotEmpty(message = "naam mag niet leeg zijn")
	private String naam;
	@NotEmpty(message = "email mag niet leeg zijn")
	private String email;
	@Temporal(TemporalType.DATE)
	@NotEmpty(message = "geboortedatum mag niet leeg zijn")
	private Date geboortedatum;
	@NotEmpty(message = "straat mag niet leeg zijn")
	private String straat;
	@NotEmpty(message = "huisnummer mag niet leeg zijn")
	private String huisnummer;
	private String busnummer;
	@NotEmpty(message = "postcode mag niet leeg zijn")
	private String postcode;
	@NotEmpty(message = "gemeente mag niet leeg zijn")
	private String gemeente;
	private int teamId;

	@Deprecated
	private Adres adres;

	@Inject
	private Bean<Team> team_bean;

	@Inject
	private Bean<Werknemer> werknemer_bean;

	@Inject
	private Bean<Credentials> credential_bean;

	public WerknemerToevoegenIO() {
		super();
	}

	// public WerknemerToevoegenIO (String naam, String voornaam, Adres adres,
	// String email, Date geboortedatum) {
	// super();
	// this.naam = naam;
	// this.voornaam = voornaam;
	// this.adres = adres;
	// this.email = email;
	// this.geboortedatum = geboortedatum;
	// }

	/**
	 * offert de werknemer
	 * 
	 * @return homepage
	 */
	public String voegWerknemerToe() {
		// Werknemer w = new Werknemer();
		// Werknemer w4 = new Werknemer(naam, voornaam, adres, email,
		// geboortedatum);

		Adres a = new Adres(straat, huisnummer, busnummer, postcode, gemeente);
		Werknemer w;
		try {
			w = new Werknemer(naam, voornaam, email, geboortedatum.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate(), a);

			werknemer_bean.offer(w);

			Credentials cred = new Credentials();
			cred.setUsername(this.email);
			cred.setWerknemer(w);
			credential_bean.offer(cred);
			// w.
			return View.HOME;
		} catch (GeboortedatumInDeToekomstException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"werknemer niet toegevoegd: geboortedatum mag niet in de toekomst liggen",
									"toevoegen niet geslaagd"));
			return null;
		}
	}

	// public String evnalueerDatum() {
	//
	// if (condition) {
	//
	// }
	// LocalDate nu = new LocalDate()
	// ChronoLocalDate dt = new chronol();
	//
	// geboortedatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(dt);
	// }

	// @EJB
	// private TeamEJB team;
	//
	// public String voegTeamToe() {
	//
	// Team t = new Team();
	// t.setNaam(naam);
	// team.aanmaken(t);
	//
	// return "home";
	// }

	public List<Team> getTeams() {
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

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

}
