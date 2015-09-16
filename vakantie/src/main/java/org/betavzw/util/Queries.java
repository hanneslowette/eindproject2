package org.betavzw.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Queries {

	/**
	 * De logger voor deze klasse
	 */
	private static final Logger logger = LogManager.getLogger(Queries.class);

	/**
	 * private default constructor zodat er geen instanties van deze klasse
	 * kunnen gemaakt worden.
	 */
	private Queries() {
		// Dummy
	}

	/**
	 * Maak een nieuwe query aan met parameters die worden meegegeven in een map.
	 * 
	 * @param c
	 * @param properties
	 * @return
	 */
	public static <T> TypedQuery<T> create(EntityManager manager, Class<T> c, Map<String, Object> properties) {
		/*
		 * Maak een nieuwe StringBuilder met als waarde de standaard select zonder voorwaarded (SELECT o FROM Class o).
		 */
		StringBuilder query_builder = new StringBuilder("SELECT o FROM ").append(c.getSimpleName()).append(" o");
		
		/*
		 * Als de map leeg is, betekent dit dat er geen parameters moeten worden toegevoegd. Anders moeten
		 * de parameters worden ingevuld in de query
		 */
		if (!properties.isEmpty()) {
			/*
			 * De voorwaarden worden meegegeven na het WHERE keywoord (SELECT o FROM Class o WHERE ...)
			 */
			query_builder.append(" WHERE ");
			
			/*
			 * Voeg alle parameters toe
			 */
			for (Iterator<Entry<String, Object>> iterator = properties.entrySet().iterator(); iterator.hasNext(); ) {
				Entry<String, Object> entry = iterator.next();
				
				/*
				 * Voeg de kolom waar de waarde in opgeslagen zit toe
				 */
				query_builder.append("o.").append(entry.getKey())
				
				/*
				 * Strings worden vergeleken met het "LIKE" keyword. De rest wordt vergeleken met het "=" teken
				 */
				.append(entry.getValue() instanceof String ? " LIKE " : "= ")
				
				/*
				 * De variabele naam is de naam van de voorwaarde met een : ervoor
				 */
				.append(":").append(entry.getKey());
				
				/*
				 * Als er nog andere voorwaarden volgen, worden deze gescheiden met het "AND" keyword
				 */
				if (iterator.hasNext()) {
					query_builder.append(" AND ");
				}
			}
		}
		
		/*
		 * Debug info voor de sysadmin
		 */
		logger.info("created query: " + query_builder.toString());
			
		/*
		 * Maak de query aan in de opgegeven entity manager
		 */
		TypedQuery<T> query = manager.createQuery(query_builder.toString(), c);
		
		/*
		 * Vul de waarden van de voorwaarden in.
		 */
		for (Iterator<Entry<String, Object>> iterator = properties.entrySet().iterator(); iterator.hasNext(); ) {
			Entry<String, Object> entry = iterator.next();
			
			/*
			 * Vul de waarde van de parameter in
			 */
			query.setParameter(entry.getKey(), entry.getValue());
		}

		/*
		 * Geef de aangemaakte query terug
		 */
		return query;
	}

	/**
	 * Maakt een nieuwe query met een array van filters
	 * 
	 * @return
	 */
	public static <T> TypedQuery<T> create(EntityManager manager, Class<T> c, Filter... filters) {
		Map<String, Object> map = new HashMap<>();
		for (Filter filter : filters) {
			map.put(filter.getColumn(), filter.getValue());
		}
		return create(manager, c, map);
	}

	/**
	 * Maakt een nieuwe query met een lijst van filters
	 * 
	 * @return
	 */
	public static <T> TypedQuery<T> create(EntityManager manager, Class<T> c, List<Filter> filters) {
		return create(manager, c, filters.toArray(new Filter[0]));
	}

}