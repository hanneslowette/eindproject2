package org.betavzw.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.betavzw.util.Toestand;

public class VerlofAanvraag {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * De start datum van de aanvraag van verlof
	 */
	private LocalDate startDatum;

	/**
	 * De eind datum van de aanvraag van het verlof
	 */
	private LocalDate eindDatum;

	/**
	 * De datum wanneer de aanvraag is geplaatst
	 */
	private LocalDate aanvraagDatum;

	/**
	 * De huidige toestand van de aanvraag
	 */
	private Toestand toestand = Toestand.PENDING;

	/**
	 * De werknemer die deze verlofaanvraag maakt
	 */
	private Werknemer werknemer;

	public LocalDate getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(LocalDate startDatum) {
		this.startDatum = startDatum;
	}

	public LocalDate getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(LocalDate eindDatum) {
		this.eindDatum = eindDatum;
	}

	public LocalDate getAanvraagDatum() {
		return aanvraagDatum;
	}

	public void setAanvraagDatum(LocalDate aanvraagDatum) {
		this.aanvraagDatum = aanvraagDatum;
	}

	public Toestand getToestand() {
		return toestand;
	}

	public void setToestand(Toestand toestand) {
		this.toestand = toestand;
	}

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
	}

	public void Goedkeuren() {
		this.toestand = Toestand.ACCEPTED;
	}

	public void Afkeuren() {
		this.toestand = Toestand.REJECTED;
	}

	public void Annuleren() {
		this.toestand = Toestand.CANCELED;
	}

}
