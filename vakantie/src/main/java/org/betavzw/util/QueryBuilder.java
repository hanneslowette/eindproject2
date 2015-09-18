package org.betavzw.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;


public class QueryBuilder {

	/**
	 * De logger voor deze klasse
	 */
	private static final Logger logger = Logger.getLogger(QueryBuilder.class);

	/**
	 * De filters die moeten worden gebruikt in de query
	 */
	private final Map<String, Object> filters = new HashMap<>();
	
	/**
	 * De kolommen waarop gesorteerd moet worden
	 */
	private final Queue<String> sort_columns = new LinkedList<>();

	/**
	 * Bouwt de query
	 * 
	 * @param c
	 * @return
	 */
	public <T> TypedQuery<T> build(EntityManager manager, Class<T> c) {
		/*
		 * Maak een nieuwe StringBuilder met als waarde de standaard select zonder voorwaarded (SELECT o FROM Class o).
		 */
		StringBuilder query_builder = new StringBuilder("SELECT o FROM ").append(c.getSimpleName()).append(" o");
		
		/*
		 * Als de map leeg is, betekent dit dat er geen parameters moeten worden toegevoegd. Anders moeten
		 * de parameters worden ingevuld in de query
		 */
		if (!filters.isEmpty()) {
			/*
			 * De voorwaarden worden meegegeven na het WHERE keywoord (SELECT o FROM Class o WHERE ...)
			 */
			query_builder.append(" WHERE ");
			
			/*
			 * Voeg alle parameters toe
			 */
			for (Iterator<Entry<String, Object>> iterator = filters.entrySet().iterator(); iterator.hasNext(); ) {
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
				.append(":").append(formatParameter(entry.getKey()));
				
				/*
				 * Als er nog andere voorwaarden volgen, worden deze gescheiden met het "AND" keyword
				 */
				if (iterator.hasNext()) {
					query_builder.append(" AND ");
				}
			}
		}
		
		/*
		 * Er moet enkel gesorteerd worden als er sort columns gespecifieerd zijn
		 */
		if (!sort_columns.isEmpty()) {
			
			/*
			 * Het sleutelwoord om te sorteren is "ORDER BY"
			 */
			query_builder.append(" ORDER BY ");
			
			/*
			 * Loop door alle sort kolommen en voeg deze toe aan de query
			 */
			for (Iterator<String> iterator = sort_columns.iterator(); iterator.hasNext(); ) {
				String column = iterator.next();
				
				/*
				 * Voeg de kolom toe
				 */
				query_builder.append("o.").append(column);
				
				/*
				 * Als er meerdere kolommen zijn waarop gesorteerd moet worden, dan moeten
				 * deze gescheiden worden met een komma
				 */
				if (iterator.hasNext()) {
					query_builder.append(", ");
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
		for (Iterator<Entry<String, Object>> iterator = filters.entrySet().iterator(); iterator.hasNext(); ) {
			Entry<String, Object> entry = iterator.next();
			
			/*
			 * Vul de waarde van de parameter in
			 */
			query.setParameter(formatParameter(entry.getKey()), entry.getValue());
		}

		/*
		 * Geef de aangemaakte query terug
		 */
		return query;
	}

	/**
	 * Voegt een filter object toe
	 * 
	 * @param filter
	 * @return
	 */
	public QueryBuilder addFilter(Filter filter) {
		return addFilter(filter.getColumn(), filter.getValue());
	}

	/**
	 * Voegt een filter toe met een gegeven naam en waarde
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public QueryBuilder addFilter(String name, Object value) {
		filters.put(name, value);
		return this;
	}

	/**
	 * Voegt een array van filters toe
	 * 
	 * @param filters
	 * @return
	 */
	public QueryBuilder addFilters(Filter... filters) {
		Arrays.stream(filters).forEach(QueryBuilder.this::addFilter);
		return this;
	}

	/**
	 * Voegt een lijst van filters toe
	 * 
	 * @param filters
	 * @return
	 */
	public QueryBuilder addFilter(List<Filter> filters) {
		return addFilters(filters.toArray(new Filter[0]));
	}

	/**
	 * De naam van de kolom waarop gesorteerd moet worden. De volgorde van
	 * toevoeging van de sorteerkolom wordt gerespecteerd
	 * 
	 * @param column
	 * @return
	 */
	public QueryBuilder sort(String column) {
		sort_columns.add(column);
		return this;
	}

	/**
	 * De naam van de kolom waarop gesorteerd moet worden. De volgorde van
	 * toevoeging van de sorteerkolom wordt gerespecteerd
	 * 
	 * @param columns
	 * @return
	 */
	public QueryBuilder sort(String... columns) {
		Arrays.stream(columns).forEach(sort_columns::add);
		return this;
	}

	/**
	 * Formats a parameter name
	 * 
	 * @param input
	 * @return
	 */
	private static String formatParameter(String input) {
		return input.replaceAll("\\.", "");
	}

}