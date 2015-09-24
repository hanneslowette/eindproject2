package org.betavzw.view.bean;

//import java.lang.reflect.Field;

//import javax.naming.directory.NoSuchAttributeException;
import javax.persistence.EntityManager;
//import javax.persistence.Id;
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
		manager.merge(entity);
	}
	
	@Override
	public void refresh(T entity) {
		manager.refresh(entity);
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
	
	/**
	 * Sets the entity manager, only used for testing
	 * @param manager
	 */
	
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}

}