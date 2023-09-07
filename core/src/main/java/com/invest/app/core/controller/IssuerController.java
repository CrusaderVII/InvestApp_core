package com.invest.app.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.data_extract.entities.Issuer;
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
}
