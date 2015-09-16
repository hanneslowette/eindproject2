package org.betavzw.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class SluitingEJB {
	
	@PersistenceContext
	private EntityManager manager;
	
	

}