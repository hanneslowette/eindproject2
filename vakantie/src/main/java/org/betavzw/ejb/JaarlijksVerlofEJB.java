package org.betavzw.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.betavzw.entities.JaarlijksVerlof;

/**
 * Session Bean implementation class JaarlijksVerlofEJB
 */
@Stateless
@LocalBean
public class JaarlijksVerlofEJB {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * Default constructor.
	 */
	public JaarlijksVerlofEJB() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public void toevoegen(int aantalDagen, int jaar) {
		JaarlijksVerlof tmp = new JaarlijksVerlof();
		tmp.setAantalDagen(aantalDagen);
		tmp.setJaar(jaar);
		manager.persist(tmp);
	}
}
