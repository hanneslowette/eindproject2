package org.betavzw.ejb;

import java.time.LocalDate;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Toestand;

/**
 * Session Bean implementation class VerlofAanvraagEJB
 */

@Stateful
public class VerlofAanvraagEJB {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * Default constructor.
	 */
	public VerlofAanvraagEJB() {
		// TODO Auto-generated constructor stub
	}

	public VerlofAanvraag verlofAanmaken(VerlofAanvraag verlofAanvraag) {
		manager.persist(verlofAanvraag);
		return verlofAanvraag;
	}

	// public VerlofAanvraag aanmaken(LocalDate startDatum, LocalDate eindDatum,
	// LocalDate aanvraagDatum) {
	// VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
	// verlofAanvraag.setStartDatum(startDatum);
	// verlofAanvraag.setEindDatum(eindDatum);
	// verlofAanvraag.setAanvraagDatum(aanvraagDatum);
	// return this.verlofAanmaken(verlofAanvraag);
	// }
	//
	// public VerlofAanvraag aanmaken(LocalDate startDatum, LocalDate eindDatum,
	// LocalDate aanvraagDatum, Werknemer werknemer) {
	// VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
	// verlofAanvraag.setStartDatum(startDatum);
	// verlofAanvraag.setEindDatum(eindDatum);
	// verlofAanvraag.setAanvraagDatum(aanvraagDatum);
	// verlofAanvraag.setWerknemer(werknemer);
	// return this.verlofAanmaken(verlofAanvraag);
	// }

	public VerlofAanvraag aanmaken(LocalDate startDatum, LocalDate eindDatum,
			LocalDate aanvraagDatum, Toestand toestand) {
		VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
		verlofAanvraag.setStartDatum(startDatum);
		verlofAanvraag.setEindDatum(eindDatum);
		verlofAanvraag.setAanvraagDatum(aanvraagDatum);
		verlofAanvraag.setToestand(toestand);
		return this.verlofAanmaken(verlofAanvraag);
	}

	public VerlofAanvraag getVerlofAanvraag(int id) {
		return manager.find(VerlofAanvraag.class, id);
	}

	public LocalDate getStartDatum() {
		return manager.find(VerlofAanvraagEJB.class, this).getStartDatum();
	}

	public LocalDate getEindDatum() {
		return manager.find(VerlofAanvraagEJB.class, this).getEindDatum();
	}

	public void setToestand(String toestand) {
		manager.setProperty("Toestand",
				Toestand.valueOf(toestand.toUpperCase()));
	}

	public Toestand getToestand() {
		return manager.find(VerlofAanvraagEJB.class, this).getToestand();
	}

}
