package com.invest.app.core.repository;

import java.io.BufferedReader;
import java.io.StringReader;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.invest.app.core.repository.request.data.DataPostfix;
import com.invest.app.core.repository.request.data.DataPrefix;

public class RequestConstructor {
	
	public static String getIssuersMetadataOnCertainLevelRequest(int level) {
		return DataPrefix.MAIN_PREFIX.value() + DataPostfix.GET_ISSUERS_ON_LEVEL.value() + level;
	}
	
	public static String getIssuersOnCertainLevelNow() {
		return DataPrefix.NOW_PREFIX.value() + DataPostfix.GET_CERTAIN_ISSUERS.value();
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
