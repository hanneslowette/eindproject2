package org.betavzw.view.bean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entity.CollectieveSluiting;
import org.betavzw.util.Filter;
import org.betavzw.util.Queries;


public class SluitingBean extends AbstractBean<CollectieveSluiting> {

	/**
	 * De entity manager
	 */
	@PersistenceContext private EntityManager manager;

	@Override
	public List<CollectieveSluiting> get(Filter... filters) {
		return Queries.create(super.getEntityManager(), CollectieveSluiting.class, filters).getResultList();
	}

}