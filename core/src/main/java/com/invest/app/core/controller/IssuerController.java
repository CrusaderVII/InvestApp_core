package com.invest.app.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.repository.Operator;

@RequestMapping("/investapp.com/issuer")
@RestController
public class IssuerController {
	
	@GetMapping("/now")
	public Issuer getIssuerNow(@RequestParam String secId) {
		Issuer issuer = Operator.getIssuerNowWithPercent(secId);
		
		return issuer;
	}
}
