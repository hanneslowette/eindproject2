package org.betavzw.view.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * 
 * @author red
 *
 * @param <T>
 */
public abstract class AbstractBean<T> implements Bean<T> {

	/**
	 * De persistence entity manager
	 */
	@PersistenceContext private EntityManager manager;

	@Override
	@Transactional public void offer(T entity) {
		manager.persist(entity);
	}

	@Override
	@Transactional public void update(T entity) {
		manager.persist(entity);
	}

	@Override
	@Transactional public void delete(T entity) {
		manager.remove(entity);
	}

	/**
	 * @return The entity manager
	 */
	public EntityManager getEntityManager() {
		return manager;
	}

}