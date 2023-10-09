package com.invest.app.repository;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.invest.app.repository.request.data.DataPostfix;
import com.invest.app.repository.request.data.DataPrefix;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;
import com.invest.app.user_operator.model.User;

public class DataRequestConstructor {
	
	public static String getStockRequest(int page) {
		return DataPrefix.DEFAULT_DATA_PREFIX.value() + DataPostfix.STOCK_POSTFIX.value() + "?page="+page;
	}
	
	public static String getStockPagesRequest() {
		return DataPrefix.DEFAULT_DATA_PREFIX.value() + DataPostfix.STOCK_PAGES_POSTFIX.value();
	}
	
	public static String getIssuerNowRequest(String secId) {
		return DataPrefix.NOW_DATA_PREFIX.value() + DataPostfix.ISSUER.value() + "?secId="+secId;
	}
	
	public static String getCertainIssuersNowRequest() {
		return DataPrefix.NOW_DATA_PREFIX.value() + DataPostfix.CERTAIN_ISSUERS.value();
	}
	
	public static String getIssuerForLastWeekRequest(String secId) {
		return DataPrefix.HISTORY_DATA_PREFIX.value() + DataPostfix.LAST_WEEK.value() + "?secId="+secId;
	}
	
	public static String getIssuerForLastMonthRequest(String secId) {
		return DataPrefix.HISTORY_DATA_PREFIX.value() + DataPostfix.LAST_MONTH.value() + "?secId="+secId;
	}
	
	public static String getIssuersMetadataOnCertainLevelRequest(int level) {
		return DataPrefix.DEFAULT_DATA_PREFIX.value() + DataPostfix.GET_ISSUERS_ON_LEVEL.value() + level;
	}
	
	public static String getIssuersOnCertainLevelNow() {
		return DataPrefix.NOW_DATA_PREFIX.value() + DataPostfix.CERTAIN_ISSUERS.value();
	}
	
	public static <T> T getResponse(String url, Class<T> responseType) {
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<T> responseEntity = template.getForEntity(url, responseType);
		
		return responseEntity.getBody();
	}
	
	public static List<Issuer> postResponse(List<IssuerMetadata> list) {
		RestTemplate template = new RestTemplate();
		List<Issuer> issuers = new ArrayList<>();
		
		ResponseEntity<List<Issuer>> responseEntity = (ResponseEntity<List<Issuer>>) template.postForEntity(getCertainIssuersNowRequest(), list, issuers.getClass());
		
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
