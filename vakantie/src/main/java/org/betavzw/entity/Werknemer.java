package org.betavzw.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;

/**
 * 
 * 
 * @author Koen De Voegt
 */
@Entity
public class Werknemer implements Comparable<Werknemer> {

	/**
	 * De voornaam van de werknemer
	 */
	private String voornaam;

	/**
	 * De achternaam van de werknemer
	 */
	private String naam;
	
	/**
	 * Het e-mail adres van de werknemer.
	 */
	private String email;

	/**
	 * De geboortedatum van de werknemer
	 */
	private LocalDate geboortedatum;

	/**
	 * De personeelsnummer van de werknemer (primary key in de databank)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int personeelsNr;

	/**
	 * Het adres (de straatnaam en huisnummer) van de werknemer
	 */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="adres_id")
	private Adres adres;

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

	public Werknemer() {}

	public Werknemer(String voornaam, String naam) {
		setNaam(naam);
		setVoornaam(voornaam);
	}
	
	public Werknemer(String voornaam, String naam, String email, LocalDate geboortedatum, Adres adres) throws GeboortedatumInDeToekomstException {
		this(voornaam, naam);
		setEmail(email);
		setGeboortedatum(geboortedatum);
		setAdres(adres);
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

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) throws GeboortedatumInDeToekomstException {
		if (geboortedatum.isAfter(LocalDate.now())) throw new GeboortedatumInDeToekomstException();
		this.geboortedatum = geboortedatum;
	}

	public int getPersoneelsNr() {
		return personeelsNr;
	}
	
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public Adres getAdres() {
		return adres;
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

	@Override
	public int compareTo(Werknemer werknemer) {
		int compare;
		compare = this.getNaam().compareTo(werknemer.getNaam());
		if (compare != 0) {
			return compare; 
		} else {
			compare = this.getVoornaam().compareTo(werknemer.getVoornaam());
			if (compare != 0) {
				return compare;
			} else {
				
				System.out.println(this.getPersoneelsNr());
				System.out.println(werknemer.getPersoneelsNr());
				compare = Integer.compare(this.getPersoneelsNr(), werknemer.getPersoneelsNr());
			}
		}
		return compare;
	}

}
