package com.invest.app.core.repository.request.user;

public enum UserPostfix {
	
	AUTH_POSTFIX("/"),
	REGISTER_POSTFIX("/save"),
	BOOKMARKS_POSTFIX("/issuers"),
	BOOKMARK_POSTFIX("/add/issuer"),
	UNBOOKMARK_POSTFIX("/delete/issuer");

	private final String value;
	
	private UserPostfix(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}

}
