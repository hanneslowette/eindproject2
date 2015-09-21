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
	public void update(T entity) {
		manager.merge(entity);
	}

//	@Override
//	@Transactional public void update(T entity) {
//		try {
//			Class<?> c = entity.getClass();
//			
//			/*
//			 * Zoek het primary key veld
//			 */
//			Field id_field = null;
//			for (Field field : c.getDeclaredFields()) {
//				/*
//				 * Maak het veld accessible
//				 */
//				field.setAccessible(true);
//				
//				/*
//				 * Als het veld de annotatie Id heeft, gebruik dit als de primary key tijdens de find
//				 */
//				id_field = field.isAnnotationPresent(Id.class) ? field : id_field;
//			}
//			
//			/*
//			 * Niets meer doen als er geen primary key is gevonden
//			 */
//			if (id_field == null) {
//				throw new NoSuchAttributeException("no unique id found");
//			}
//			
//			/*
//			 * Haal het object uit de database
//			 */
//			Object fetch = manager.find(c, id_field.get(entity));
//			
//			/*
//			 * Kopieer het object
//			 */
//			for (Field field : c.getDeclaredFields()) {
//				if (!field.isAnnotationPresent(Id.class)) {
//					/*
//					 * Maak het veld accessible
//					 */
//					field.setAccessible(true);
//					System.out.println("Copying " + field.getName() + " [" + field.get(entity) + "] to fetched object");
//					
//					/*
//					 * Zet de waarde van het veld in de database entity naar de opgegeven entity
//					 */
//					field.set(fetch, field.get(entity));
//				}
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

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