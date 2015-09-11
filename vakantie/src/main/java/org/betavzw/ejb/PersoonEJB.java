package org.betavzw.ejb;

import java.time.LocalDate;
import java.util.Set;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entities.JaarlijksVerlof;
import org.betavzw.entities.Team;
import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.entities.Werknemer;

/**
 * 
 * @author user104
 *
 */
@Stateful
public class PersoonEJB {

	/**
	 * 
	 */
	@PersistenceContext
	private EntityManager manager;

	public void toevoegen(Werknemer w) {
		manager.persist(w);
	}
	
	public void toevoegen(String naam, String voornaam, String adres,
			String email, LocalDate geboortedatum) {
		Werknemer w = new Werknemer();
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