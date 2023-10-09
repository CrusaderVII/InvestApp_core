package com.invest.app.repository.request.data;

public enum DataPrefix {

	NOW_DATA_PREFIX ("http://localhost:8080/api/data/now"),
	
	HISTORY_DATA_PREFIX("http://localhost:8080/api/data/history"),
	
	DEFAULT_DATA_PREFIX ("http://localhost:8080/api/data");
	
	private final String value;
	
	private DataPrefix(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
