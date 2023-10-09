package com.invest.app.user_operator.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestConstructor {
	
	public static String getAllIssuersMetadataRequest() {
		return "http://localhost:8080/api/data/issuers";
	}
	
	public static <T> T getAllIssuersMetadataResponse(Class<T> responseType) {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<T> responseEntity = template.getForEntity(RequestConstructor.getAllIssuersMetadataRequest(), responseType);
		
		return responseEntity.getBody();
	}
}
