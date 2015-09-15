package org.betavzw.util;

public class Filter {

	private final String column;
	
	private final String value;

	public Filter(String column, String value) {
		this.column = column;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public String getValue() {
		return value;
	}
	

}