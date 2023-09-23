package com.invest.app.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;
import com.invest.app.data_extract.entities.PageMetadata;
import com.invest.app.data_extract.repository.Operator;

@CrossOrigin(originPatterns = "http://localhost:4200/")
@RestController
@RequestMapping("/investapp.com")
public class MainController {
	
	@GetMapping("/stock")
	public List<Issuer> allIssuers(@RequestParam int page) {
		List<Issuer> issuers = Operator.getIssuersOnCertainLevelNow(1);
		
		int start = 10 * (page-1);
		int end = (start/10==issuers.size()/10)? start+issuers.size()%10 : start+10;
		
		return issuers.subList(start, end);
	}
	
	@GetMapping("/stock/pages")
	public PageMetadata getPages() {
		List<IssuerMetadata> issuers = Operator.getIssuersMetadataOnCertainLevel(1);
				
		return new PageMetadata(issuers.size()/10+1);
	}
}
