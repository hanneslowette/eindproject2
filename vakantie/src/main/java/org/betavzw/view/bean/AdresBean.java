package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entity.Adres;
import org.betavzw.util.QueryBuilder;

public class AdresBean extends AbstractBean<Adres> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * De entity manager
	 */
	@PersistenceContext private EntityManager manager;

	@Override
	public List<Adres> get(QueryBuilder builder) {
		return builder.build(super.getEntityManager(), Adres.class).getResultList();
	}

}