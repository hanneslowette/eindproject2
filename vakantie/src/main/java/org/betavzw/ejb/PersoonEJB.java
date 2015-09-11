package org.betavzw.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author user104
 *
 */
@LocalBean
@Stateless public class PersoonEJB {

	/**
	 * 
	 */
	@PersistenceContext private EntityManager manager;

}