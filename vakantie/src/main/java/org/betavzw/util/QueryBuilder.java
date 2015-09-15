package org.betavzw.util;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QueryBuilder {

	public static <T> TypedQuery<T> create(EntityManager manager, Class<T> c, Filter... filters) {
		StringBuilder query_builder = new StringBuilder("SELECT o FROM ").append(c.getSimpleName());
		
		/*
		 * Bouw de query op
		 */
		if (filters.length > 0) {
			query_builder.append(" WHERE ");
			for (Filter filter : filters) {
				query_builder.append("o.").append(filter.getColumn())
						.append((filter.getValue() instanceof String) ? " LIKE " : "= ")
						.append(":").append(filter.getColumn());
			}
		}
		
		/*
		 * Maak de query aan
		 */
		TypedQuery<T> query = manager.createQuery(query_builder.toString(), c);
		
		/*
		 * Zet de waarden van de HQL variabelen
		 */
		for (Filter filter : filters) {
			query.setParameter(":" + filter.getColumn(), filter.getValue());
		}
		
		return query;
	}

}