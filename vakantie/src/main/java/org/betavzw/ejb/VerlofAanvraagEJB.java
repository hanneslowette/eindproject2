package org.betavzw.ejb;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;
import org.betavzw.util.Toestand;

/**
 * Session Bean implementation class VerlofAanvraagEJB
 */

@Stateful
public class VerlofAanvraagEJB implements Serializable, IVerlofAanvraag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * TODO: naam persistence context
	 */
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

	/**
	 * Maakt een nieuw verlofaanvraag aan met de gegeven waarden en geeft het
	 * aangemaakte verlofaanvraag terug
	 * 
	 * @param startdatum
	 * @param einddatum
	 * @param aanvraagdatum
	 * @param toestand
	 * @param werknemer
	 * @return
	 */
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

	/**
	 * Persisteert de verlofaanvraag
	 * 
	 * @param verlofaanvraag
	 */
	public void commit(VerlofAanvraag verlofAanvraag) {
		manager.persist(verlofAanvraag);
	}

	/**
	 * Krijg een verlofaanvraag uit de database aan de hand van de id
	 * 
	 * @param id
	 * @return
	 */
	public VerlofAanvraag getVerlofAanvraagId(int id) {
		return manager.find(VerlofAanvraag.class, id);
	}
	
	/**
	 * Krijg een verlofaanvraag uit de database aan de hand van de startdatum
	 * 
	 * @param id
	 * @return
	 */
	public VerlofAanvraag getVerlofAanvraagStartDatum(LocalDate startDatum) {
		return manager.find(VerlofAanvraag.class, startDatum);
	}
	
	/**
	 * Krijg een verlofaanvraag uit de database aan de hand van de einddatum
	 * 
	 * @param id
	 * @return
	 */
	public VerlofAanvraag getVerlofAanvraagEindDatum(LocalDate eindDatum) {
		return manager.find(VerlofAanvraag.class, eindDatum);
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

	@Override
	public List<VerlofAanvraag> getVerlofAanvragen(Filter... filters) {
		return QueryBuilder.create(manager, VerlofAanvraag.class, filters)
				.getResultList();
	}

	@Override
	@Transactional
	public void voegVerlofAanvraagToe(VerlofAanvraag verlofAanvraag) {
		manager.persist(verlofAanvraag);
	}

	@Override
	@Transactional
	public void verwijderVerlofAanvraag(VerlofAanvraag verlofAanvraag) {
		manager.remove(verlofAanvraag);
	}

	@Override
	@Transactional
	public void wijzigVerlofAanvraag(VerlofAanvraag verlofAanvraag) {
		manager.persist(verlofAanvraag);
	}

}
