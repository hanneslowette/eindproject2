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
			LocalDate aanvraagDatum, Toestand toestand, Werknemer werknemer) {
		VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
		verlofAanvraag.setStartDatum(startDatum);
		verlofAanvraag.setEindDatum(eindDatum);
		verlofAanvraag.setAanvraagDatum(aanvraagDatum);
		verlofAanvraag.setToestand(toestand);
		verlofAanvraag.setWerknemer(werknemer);
		return this.verlofAanmaken(verlofAanvraag);
	}

	public VerlofAanvraag get(int id) {
		return manager.find(VerlofAanvraag.class, id);
	}

}
