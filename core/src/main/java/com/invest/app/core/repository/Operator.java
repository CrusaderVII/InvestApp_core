package com.invest.app.core.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.invest.app.core.json_parser.SimpleJsonParser;
import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;

public class Operator {

	
	public static List<IssuerMetadata> getIssuersMetadataOnCertainLevel(int level) {
		BufferedReader br = DataRequestConstructor.getPlainJson(DataRequestConstructor.getIssuersMetadataOnCertainLevelRequest(level));
		List<IssuerMetadata> issuersMetadata;
		
		String jsonString = readJson(br);
		
		try {
			issuersMetadata = SimpleJsonParser.getIssuersMetadataOnCertainLevel(
					SimpleJsonParser.parse(jsonString));
					
		} catch (IOException e) {
			issuersMetadata = null;
			
			e.printStackTrace();
		}
		
		return issuersMetadata;
	}
	
	public static List<Issuer> getIssuersOnCertainLevelNow(int level) {
		List<IssuerMetadata> issuersMetadata = Operator.getIssuersMetadataOnCertainLevel(level);
		List<Issuer> issuers;
		
		BufferedReader br = DataRequestConstructor.getPlainJsonWithBody(issuersMetadata, 
				DataRequestConstructor.getIssuersOnCertainLevelNow());
		
		String jsonString = readJson(br);
		
		try {
			issuers = SimpleJsonParser.getIssuersOnCertainLevelNow(SimpleJsonParser.parse(jsonString));
		} catch (IOException e) {
			issuers = null;			//e.printStackTrace();
		}
		
		return issuers;
	}
	
	public static List<Issuer> getMainIssuersNow() {
		List<IssuerMetadata> metadata;
		Resource issuers = new ClassPathResource("static/Main_issuers.txt"); 
		File file = null;
		try {
			file = issuers.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String jsonString = readJson(br);
		
		try {
			metadata = SimpleJsonParser.getMainIssuersMetadata(SimpleJsonParser.parse(jsonString));
		} catch (IOException e) {
			metadata = null;
			e.printStackTrace();
		}
		
		return metadata.stream()
				.map(issuerMetadata -> com.invest.app.data_extract.repository.Operator.getIssuerNowWithPercent(issuerMetadata.getSecId()))
				.toList();
	}
	
	private static String readJson(BufferedReader br) {
		String output;
        StringBuilder builder = new StringBuilder();
        
		try {
   			do {
               	output = br.readLine();
               	
               	if(output!=null) builder.append(output+'\n');
               	
               	;
              } while (output != null );
   			
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
		
		return builder.toString();
	}
}
