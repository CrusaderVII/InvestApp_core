package com.invest.app.core.repository;

import java.io.BufferedReader;
import java.io.StringReader;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.invest.app.core.repository.request.data.DataPostfix;
import com.invest.app.core.repository.request.data.DataPrefix;
import com.invest.app.core.repository.request.user.UserPostfix;
import com.invest.app.core.repository.request.user.UserPrefix;
import com.invest.app.user_operator.model.User;

import static com.invest.app.core.repository.request.user.UserPrefix.DEFAULT_USER_PREFIX;

public class UserRequestConstructor {
	
	public static String getUserAuthRequest(String password, String email) {
		return DEFAULT_USER_PREFIX.value() + UserPostfix.AUTH_POSTFIX.value() + "?email="+email+"&password="+password;
	}
	
	public static String getIssuersMetadataOnCertainLevelRequest(int level) {
		return DataPrefix.MAIN_PREFIX.value() + DataPostfix.GET_ISSUERS_ON_LEVEL.value() + level;
	}
	
	public static String getIssuersOnCertainLevelNow() {
		return DataPrefix.NOW_PREFIX.value() + DataPostfix.GET_CERTAIN_ISSUERS.value();
	}
	
	public static User getUserResponse (String request) {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<User> responseEntity = template.getForEntity(request, User.class);
				
		return responseEntity.getBody();
	}

	public static BufferedReader getPlainJson(String request) {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<String> responseEntity = template.getForEntity(request, String.class);
				
		return new BufferedReader(new StringReader(responseEntity.getBody()));
	}
	
	public static <T> BufferedReader getPlainJsonWithBody(T body, String request) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<T> requestBody = new HttpEntity<>(body);
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(request, requestBody, String.class);
		
		BufferedReader br = new BufferedReader(new StringReader(responseEntity.getBody()));
		
		return br;
	}
}
