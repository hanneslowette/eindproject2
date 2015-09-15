package org.betavzw.ejb;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.betavzw.entities.Adres;
import org.betavzw.entities.JaarlijksVerlof;
import org.betavzw.entities.Team;
import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;

/**
 * 
 * @author user104
 *
 */
@ApplicationScoped
public class WerknemerEJB implements IWerknemer, Serializable {

	/**
	 * Versie id van het geserializeerd object
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@PersistenceContext
	private EntityManager manager;

	public void toevoegen(Werknemer w) {
		manager.persist(w);
	}

	@Override
	public List<Werknemer> getWerknemers(Filter... filters) {
		return QueryBuilder.create(manager, Werknemer.class, filters).getResultList();
	}
	
	public void toevoegen(String naam, String voornaam, Adres adres, String email, LocalDate geboortedatum) {Werknemer w = new Werknemer();
		w.setNaam(naam);
		w.setVoornaam(voornaam);
		w.setAdres(adres);
		w.setEmail(email);
		w.setGeboortedatum(geboortedatum);
		manager.persist(w);
	}
	
	public void setPaswoord(int id, String paswoord){
		Werknemer w = manager.find(Werknemer.class, id);
		w.setPaswoord(paswoord);
		manager.persist(w);
	}
	
	public void setPostcode(int id, int postcode){
		Werknemer w = manager.find(Werknemer.class, id);
		w.setPostcode(postcode);
		manager.persist(w);
	}
	
	public void setTeam(int id, Team team){
		Werknemer w = manager.find(Werknemer.class, id);
		w.setTeam(team);
		manager.persist(w);
	}
	
	public void setJaarlijksVerlof(int id, Set<JaarlijksVerlof> jaarlijkseVerloven){
		Werknemer w = manager.find(Werknemer.class, id);
		w.setJaarlijkseVerloven(jaarlijkseVerloven);
		manager.persist(w);
	}
	
	public void setVerlofAanvraag(int id, Set<VerlofAanvraag> verlofAanvragen){
		Werknemer w = manager.find(Werknemer.class, id);
		w.setVerlofAanvragen(verlofAanvragen);
		manager.persist(w);
	}
	
	public Werknemer getWerknemer(int id){
		return manager.find(Werknemer.class, id);
	}

	@Override
	@Transactional public void voegWerknemerToe(Werknemer werknemer) {
		manager.persist(werknemer);
	}
	
	@Override
	@Transactional public void verwijderWerknemer(Werknemer werknemer) {
		manager.remove(werknemer);
	}

	@Override
	@Transactional public void wijzigWerknemer(Werknemer werknemer) {
		manager.persist(werknemer);
	}
	
}