package org.betavzw.view.bean;

import java.util.List;

import org.betavzw.util.Filter;

/**
 * Stelt een abstracte bean voor
 * 
 * @author red
 *
 * @param <T>
 */
public interface Bean<T> {

	/**
	 * Haalt de resultaten van een query
	 * 
	 * @param filters
	 * @return
	 */
	public abstract List<T> get(Filter... filters);
	
	/**
	 * Probeert een entity te persisteren
	 * 
	 * @param entity
	 */
	public abstract void offer(T entity);
	
	/**
	 * Probeert een entity te updaten.
	 * @param entity
	 */
	public abstract void update(T entity);
	
	/**
	 * Probeert een entity te verwijderen
	 * 
	 * @param entity
	 */
	public abstract void delete(T entity);
	
	/**
	 * Krijgt een lijst van entities terug met een lijst van filters
	 * 
	 * @param filters
	 * @return
	 */
	default List<T> get(List<Filter> filters) {
		return get(filters.toArray(new Filter[0]));
	}
	
	/**
	 * Krijg een enkele waarde terug van een query
	 * 
	 * @param filters
	 * @return
	 */
	default T getSingle(Filter... filters) {
		List<T> result = get(filters);
		if (result.size() == 1)
			return get(filters).get(0);
		throw new IllegalStateException("getSingle() must return just one result (size="+result.size()+")");
	}

}