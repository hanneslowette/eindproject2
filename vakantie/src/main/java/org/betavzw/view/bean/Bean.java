package org.betavzw.view.bean;

import java.util.List;

import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;

/**
 * Stelt een abstracte bean voor
 * 
 * @author red
 *
 * @param <T>
 */
public interface Bean<T> {
	
	/**
	 * Haalt de resultaten van een custom query
	 * 
	 * @param builder
	 * @return
	 */
	public abstract List<T> get(QueryBuilder query);
	
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
	 * Haalt de resultaten van een query
	 * 
	 * @param filters
	 * @return
	 */
	default List<T> get(Filter... filters) {
		return get(new QueryBuilder().addFilters(filters));
	}
	
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
			return result.get(0);
		throw new IllegalStateException("getSingle() must return just one result (size="+result.size()+")");
	}
	
	/**
	 * Krijg een enkele waarde terug van een query
	 * 
	 * @param filters
	 * @return
	 */
	default T getSingle(QueryBuilder query) {
		List<T> result = get(query);
		if (result.size() == 1)
			return result.get(0);
		throw new IllegalStateException("getSingle() must return just one result (size="+result.size()+")");
	}

}