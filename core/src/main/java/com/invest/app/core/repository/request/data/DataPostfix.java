package com.invest.app.core.repository.request.data;

public enum DataPostfix {
	
	GET_ISSUERS_ON_LEVEL ("/issuers/level?level="),
	
	STOCK_POSTFIX("/stock"),
	
	STOCK_PAGES_POSTFIX("/stock/pages"),
	
	ISSUER("/issuer"),
	
	LAST_WEEK("/last/week"),
	
	LAST_MONTH("/last/month"),
	
	CERTAIN_ISSUERS("/issuers/certain");
	
	private final String value;
	
	private DataPostfix(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
