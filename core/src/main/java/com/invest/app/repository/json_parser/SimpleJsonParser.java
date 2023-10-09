package com.invest.app.repository.json_parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;
public class SimpleJsonParser {
	public static ObjectMapper mapper = getObjectMapper();
	
	private static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper;
	}
	
	public static JsonNode parse(String json) throws IOException {
		
		return mapper.readTree(json);
	}
	
	public static List<IssuerMetadata> getIssuersMetadataOnCertainLevel(JsonNode jsonNode) {
		List<IssuerMetadata> issuersMetadata = new ArrayList<>();
		
		jsonNode.iterator()
				.forEachRemaining(innerNode -> 
				{
					try {
						issuersMetadata.add(mapper.treeToValue(innerNode, IssuerMetadata.class));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				});
				
		return issuersMetadata;
	}
	
	public static List<Issuer> getIssuersOnCertainLevelNow(JsonNode jsonNode) {
		List<Issuer> issuers = new ArrayList<>();
		
		jsonNode.iterator()
			.forEachRemaining(innerNode -> 
			{
				try {
					issuers.add(mapper.treeToValue(innerNode, Issuer.class));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			});
		
		return issuers;
	}
	
	public static List<IssuerMetadata> getMainIssuersMetadata(JsonNode jsonNode) {
		List<IssuerMetadata> metadata = new ArrayList<>();
		
		jsonNode.iterator()
			.forEachRemaining(innerNode -> 
					{
						try {
							metadata.add(mapper.treeToValue(innerNode, IssuerMetadata.class));
						} catch (JsonProcessingException | IllegalArgumentException e) {
							e.printStackTrace();
						}
					});
		
		return metadata;
	}
}
