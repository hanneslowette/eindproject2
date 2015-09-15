package org.betavzw.util;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QueryBuilder {

	/**
	 * Maakt een query met verschillende filters aan
	 * 
	 * @param manager
	 * @param c
	 * @param filters
	 * @return
	 */
	public static <T> TypedQuery<T> create(EntityManager manager, Class<T> c, Filter... filters) {
		StringBuilder query_builder = new StringBuilder("SELECT o FROM ").append(c.getSimpleName()).append(" o");
		
		/*
		 * Bouw de query op
		 */
		if (filters.length > 0) {
			query_builder.append(" WHERE ");
			for (int i = 0; i < filters.length; i++) {
				Filter filter = filters[i];
				
				/*
				 * Voeg alle checks toe (in de vorm van o.column= :column
				 */
				query_builder.append("o.").append(filter.getColumn())
						.append((filter.getValue() instanceof String) ? " LIKE " : "= ")
						.append(":").append(filter.getColumn());
				
				/*
				 * Scheid de verschillende checks met AND
				 */
				if (i < filters.length - 1) {
					query_builder.append(" AND ");
				}
			}
		}
		
		/*
		 * Maak de query aan
		 */
		TypedQuery<T> query = manager.createQuery(query_builder.toString(), c);
		
		/*
		 * Vul de parameters in (de parameter is in de vorm van ":column")
		 */
		for (Filter filter : filters) {
			query.setParameter(filter.getColumn(), filter.getValue());
		}
		
		return query;
	}

}