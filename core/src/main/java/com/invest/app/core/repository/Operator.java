package com.invest.app.core.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.invest.app.core.json_parser.SimpleJsonParser;
import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;

public class Operator {

	
	public static List<IssuerMetadata> getIssuersMetadataOnCertainLevel(int level) {
		BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getIssuersMetadataOnCertainLevelRequest(level));
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
		
		BufferedReader br = RequestConstructor.getPlainJsonWithBody(issuersMetadata, 
				RequestConstructor.getIssuersOnCertainLevelNow());
		
		String jsonString = readJson(br);
		
		try {
			issuers = SimpleJsonParser.getIssuersOnCertainLevelNow(SimpleJsonParser.parse(jsonString));
		} catch (IOException e) {
			issuers = null;
			System.out.println("lol");
			//e.printStackTrace();
		}
		
		return issuers;
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
