package com.invest.app.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.core.repository.Operator;
import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;

@CrossOrigin(originPatterns = "http://localhost:4200/")
@RestController
@RequestMapping("/investapp.com")
public class MainController {
	
	@GetMapping("/stock")
	public List<Issuer> allIssuers() {
		List<Issuer> issuers = com.invest.app.data_extract.repository.Operator.getIssuersOnCertainLevelNow(1);
		
		return issuers;
	}
}
