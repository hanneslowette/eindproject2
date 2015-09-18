package org.betavzw.view.bean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entity.CollectieveSluiting;
import org.betavzw.util.QueryBuilder;


public class SluitingBean extends AbstractBean<CollectieveSluiting> {

	/**
	 * De entity manager
	 */
	@PersistenceContext private EntityManager manager;

	@Override
	public List<CollectieveSluiting> get(QueryBuilder query) {
		return query.build(super.getEntityManager(), CollectieveSluiting.class).getResultList();
	}

}