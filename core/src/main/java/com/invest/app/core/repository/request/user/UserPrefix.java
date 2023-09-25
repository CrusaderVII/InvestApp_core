package com.invest.app.core.repository.request.user;

public enum UserPrefix {
	
	DEFAULT_USER_PREFIX ("http://localhost:8090/api/user");

	private final String value;
	
	private UserPrefix(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
