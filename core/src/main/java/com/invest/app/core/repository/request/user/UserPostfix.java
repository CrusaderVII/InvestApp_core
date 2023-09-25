package com.invest.app.core.repository.request.user;

public enum UserPostfix {
	
	AUTH_POSTFIX("/");

	private final String value;
	
	private UserPostfix(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}

}
