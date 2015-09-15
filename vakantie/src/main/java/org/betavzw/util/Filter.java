package org.betavzw.util;

public class Filter {

	/**
	 * 
	 */
	private final String column;
	
	/**
	 * De door de gebruiker opgegeven waarde
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