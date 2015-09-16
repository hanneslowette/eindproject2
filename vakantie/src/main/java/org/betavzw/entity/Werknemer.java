package org.betavzw.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * 
 * @author Koen De Voegt
 */
@Entity
public class Werknemer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * De achternaam van de werknemer
	 */
	private String naam;

	/**
	 * De voornaam van de werknemer
	 */
	private String voornaam;

	/**
	 * Het e-mail adres van de gebruiker. Gebruikt als username bij login.
	 */
	private String email;

	/**
	 * De geboortedatum van de werknemer
	 */
	private LocalDate geboortedatum;

	/**
	 * De personeelsnummer van de werknemer (primary key in de databank)
	 */
	private int personeelsNr;

	/**
	 * Het adres (de straatnaam en huisnummer) van de werknemer
	 */
	private Adres adres;

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	/**
	 * De postcode van de gemeente waar de werknemer woont
	 */
	private int postcode;

	/**
	 * De naam van de gemeente waar de werknemer woont
	 */
	private String gemeente;

	/**
	 * Het wachtwoord van de werknemer
	 */
	private String paswoord;

	/**
	 * Het team waartoe deze werknemer geboord
	 */
	@ManyToOne
	private Team team;

	/**
	 * Het aantal verlofdagen dat deze werknemer in gegeven jaar heeft
	 */
	@OneToMany
	private Set<JaarlijksVerlof> jaarlijkseVerloven = new HashSet<JaarlijksVerlof>();

	/**
	 * De verlofaanvragen van deze werknemer
	 */
	@OneToMany
	private Set<VerlofAanvraag> verlofAanvragen = new HashSet<VerlofAanvraag>();

	public Werknemer() {
		super();
	}

	// public Werknemer(String naam, String voornaam, String email,
	// LocalDate geboortedatum, String adres) {
	// super();
	// this.naam = naam;
	// this.voornaam = voornaam;
	// this.email = email;
	// this.geboortedatum = geboortedatum;
	// //this.adres = adres;
	// }

	public Werknemer(String naam, String voornaam, String email,
			LocalDate geboortedatum, Adres adres) {
		// TODO Auto-generated constructor stub
		this.naam = naam;
		this.voornaam = voornaam;
		this.email = email;
		this.geboortedatum = geboortedatum;
		this.adres = adres;
	}

	public Werknemer(String voornaam, String naam, int personeelsNr) {
		this.naam = naam;
		this.voornaam = voornaam;
		this.personeelsNr = personeelsNr;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public int getPersoneelsNr() {
		return personeelsNr;
	}

	public void setPersoneelsNr(int personeelsNr) {
		this.personeelsNr = personeelsNr;
	}

	// public String getAdres() {
	// return adres;
	// }
	//
	// public void setAdres(String adres) {
	// this.adres = adres;
	// }

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Set<JaarlijksVerlof> getJaarlijkseVerloven() {
		return jaarlijkseVerloven;
	}

	public void setJaarlijkseVerloven(Set<JaarlijksVerlof> jaarlijkseVerloven) {
		this.jaarlijkseVerloven = jaarlijkseVerloven;
	}

	public void addJaarlijksVerlof(JaarlijksVerlof jaarlijksVerlof) {
		jaarlijkseVerloven.add(jaarlijksVerlof);
	}

	public Set<VerlofAanvraag> getVerlofAanvragen() {
		return verlofAanvragen;
	}

	public void setVerlofAanvragen(Set<VerlofAanvraag> verlofAanvragen) {
		this.verlofAanvragen = verlofAanvragen;
	}

	public void addVerlofAanvraag(VerlofAanvraag verlofAanvraag) {
		verlofAanvragen.add(verlofAanvraag);
	}

	public int getId() {
		return id;
	}

}
