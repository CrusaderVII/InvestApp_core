package com.invest.app.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.invest.app.repository.request.user.UserPostfix;
import com.invest.app.data_extract.entities.IssuerMetadata;
import com.invest.app.user_operator.model.User;

import static com.invest.app.repository.request.user.UserPrefix.DEFAULT_USER_PREFIX;

public class UserRequestConstructor {
	
	public static String getUserAuthRequest(String password, String email) {
		return DEFAULT_USER_PREFIX.value() + UserPostfix.AUTH_POSTFIX.value() + "?email="+email+"&password="+password;
	}
	
	public static String saveUserRequest() {
		return DEFAULT_USER_PREFIX.value() + UserPostfix.REGISTER_POSTFIX.value();
	}
	
	public static String getUsersBookmarksRequest(String userName) {
		return DEFAULT_USER_PREFIX.value() + UserPostfix.BOOKMARKS_POSTFIX.value() + "?userName="+userName;
	}
	
	public static String bookmarkIssuerRequest(String userName, String secId) {
		return DEFAULT_USER_PREFIX.value() + UserPostfix.BOOKMARK_POSTFIX.value() + "?userName="+userName+"&secId="+secId;
	}
	
	public static String unbookmarkIssuerRequest(String userName, String secId) {
		return DEFAULT_USER_PREFIX.value() + UserPostfix.UNBOOKMARK_POSTFIX.value() + "?userName="+userName+"&secId="+secId;
	}
	
	public static void deleteResponse(String userName, String secId) {
		RestTemplate template = new RestTemplate();
		
		template.delete(unbookmarkIssuerRequest(userName, secId));
	}
	
	public static User postResponse(User user) {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<User> responseEntity = template.postForEntity(saveUserRequest(), user, User.class);
		
		return responseEntity.getBody();
	}
	
	public static <T> T getResponse(String url, Class<T> responseType) {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<T> responseEntity = template.getForEntity(url, responseType);
		
		return responseEntity.getBody();
	}
}
