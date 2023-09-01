package com.invest.app.core.repository.request.data;

public enum DataPrefix {

	NOW_PREFIX ("http://localhost:8080/now"),
	
	MAIN_PREFIX ("http://localhost:8080/");
	
	private final String value;
	
	private DataPrefix(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
