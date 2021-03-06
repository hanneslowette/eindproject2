package org.betavzw.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entities.JaarlijksVerlof;

/**
 * Session Bean implementation class JaarlijksVerlofEJB
 */
@Stateful
public class JaarlijksVerlofEJB {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * Default constructor.
	 */
	public JaarlijksVerlofEJB() {
		// TODO Auto-generated constructor stub
	}

	public void toevoegen(int aantalDagen, int jaar) {
		JaarlijksVerlof tmp = new JaarlijksVerlof();
		tmp.setAantalDagen(aantalDagen);
		tmp.setJaar(jaar);
		manager.persist(tmp);
	}
}
