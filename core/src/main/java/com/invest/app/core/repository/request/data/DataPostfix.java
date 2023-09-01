package com.invest.app.core.repository.request.data;

public enum DataPostfix {

	GET_CERTAIN_ISSUERS ("/level/issuers"),
	
	GET_ISSUERS_ON_LEVEL ("/issuers/level?level=");
	
	private final String value;
	
	private DataPostfix(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
