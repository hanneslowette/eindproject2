package org.betavzw.util;

public class Filter {

	/**
	 * De naam van de kolom. Meestal de naam van de variable in de klasse waar je op wil vergelijken
	 */
	private final String column;
	
	/**
	 * De waarde van de voorwaarde
	 */
	private final Object value;

	public Filter(String column, Object value) {
		this.column = column;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public Object getValue() {
		return value;
	}

}