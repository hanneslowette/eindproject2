package org.betavzw.test;

import org.betavzw.util.Filter;
import org.junit.Test;

@Deprecated
public class WerknemerQueryTest {

	@Test
	public void testWerknemer() {
		Filter[] filters = {
				new Filter("naam", "hannes"),
				new Filter("voornaam", "lowette")
		};
		StringBuilder query_builder = new StringBuilder("SELECT w FROM Werknemer");
		
		/*
		 * Bouw de query op
		 */
		if (filters.length > 0) {
			query_builder.append(" WHERE ");
			for (Filter filter : filters) {
				query_builder.append(filter.getColumn())
						.append((filter.getValue() instanceof String) ? " LIKE " : "= ")
						.append(":").append(filter.getColumn());
			}
		}
		
		System.out.println(query_builder.toString());
	}

}