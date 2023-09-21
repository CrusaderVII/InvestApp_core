package com.invest.app.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;
import com.invest.app.data_extract.repository.Operator;

@CrossOrigin(originPatterns = "http://localhost:4200/")
@RequestMapping("/investapp.com/issuer")
@RestController
public class IssuerController {
	
	@GetMapping("/now")
	public Issuer getIssuerNow(@RequestParam String secId) {
		Issuer issuer = Operator.getIssuerNowWithPercent(secId);
		
		return issuer;
	}
	
	@GetMapping("/last-month")
	public List<Issuer> getIssuerForLastMonth(@RequestParam String secId) {
		List<Issuer> issuerForLastMonth = Operator.getIssuerForLastMonth(secId);
		
		return issuerForLastMonth;
	}
	
	@GetMapping("/main/now")
	public List<Issuer> getMainIssuersNow() {
		List<Issuer> issuers = com.invest.app.core.repository.Operator.getMainIssuersNow();
		
		return issuers;
	}
	
	@PostMapping("/certain")
	public List<Issuer> getIssuersOnCertainLevelNow(@RequestBody List<IssuerMetadata> list) {
		List<Issuer> issuersNow = list.stream()
				.map(issuerMetadata -> Operator.getIssuerNowWithPercent(issuerMetadata.getSecId()))
				.toList();
		
		return issuersNow;
	}
}
