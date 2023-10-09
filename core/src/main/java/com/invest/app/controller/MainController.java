package com.invest.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.repository.DataRequestConstructor;
import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;
import com.invest.app.data_extract.entities.PageMetadata;
import com.invest.app.data_extract.repository.Operator;

import static com.invest.app.repository.DataRequestConstructor.getResponse;

@CrossOrigin(originPatterns = "http://localhost:4200/")
@RestController
@RequestMapping("/investapp.com")
public class MainController {
	
	@GetMapping("/stock")
	public List<Issuer> allIssuers(@RequestParam int page) {
		List<Issuer> issuers = new ArrayList<>();
		
		issuers = getResponse(DataRequestConstructor.getStockRequest(page), issuers.getClass());
		
		return issuers;
	}
	
	@GetMapping("/stock/pages")
	public PageMetadata getPages() {
		PageMetadata pages = new PageMetadata(0);
		
		pages = getResponse(DataRequestConstructor.getStockPagesRequest(), pages.getClass());
				
		return pages;
	}
}
