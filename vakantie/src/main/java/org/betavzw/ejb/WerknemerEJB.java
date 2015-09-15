package org.betavzw.ejb;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.betavzw.entities.Adres;
import org.betavzw.entities.JaarlijksVerlof;
import org.betavzw.entities.Team;
import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;

/**
 * 
 * @author user104
 *
 */
@Stateful
public class WerknemerEJB implements IWerknemer {

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
		StringBuilder query_builder = new StringBuilder("SELECT w FROM Werknemer");
		
		/*
		 * Bouw de query op
		 */
		if (filters.length > 0) {
			query_builder.append(" WHERE ");
			for (Filter filter : filters) {
				query_builder.append(filter.getColumn())
						.append((filter.getValue() instanceof String) ? " LIKE " : "= ")
						.append(":").append(filter.getColumn());
			}
		}
		
		/*
		 * Maak de query aan
		 */
		TypedQuery<Werknemer> query = manager.createQuery(query_builder.toString(), Werknemer.class);
		
		/*
		 * Zet de waarden van de HQL variabelen
		 */
		for (Filter filter : filters) {
			query.setParameter(":" + filter.getColumn(), filter.getValue());
		}
		return query.getResultList();
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
	
	public void postcodeSetten(int id, int postcode){
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

}