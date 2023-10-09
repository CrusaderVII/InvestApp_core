package com.invest.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.invest.app.repository.Operator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.repository.DataRequestConstructor;
import com.invest.app.data_extract.entities.Issuer;
import com.invest.app.data_extract.entities.IssuerMetadata;

import static com.invest.app.repository.DataRequestConstructor.getResponse;

@CrossOrigin(originPatterns = "http://localhost:4200/")
@RequestMapping("/investapp.com/issuer")
@RestController
public class IssuerController {
	
	@GetMapping("/now")
	public Issuer getIssuerNow(@RequestParam String secId) {
		Issuer issuer = new Issuer();
		
		issuer = getResponse(DataRequestConstructor.getIssuerNowRequest(secId), issuer.getClass());
		
		return issuer;
	}
	
	@GetMapping("/main/now")
	public List<Issuer> getMainIssuersNow() {
		List<Issuer> issuers = Operator.getMainIssuersNow();
		
		return issuers;
	}
	
	@GetMapping("/last-month")
	public List<Issuer> getIssuerForLastMonth(@RequestParam String secId) {
		List<Issuer> issuerForLastMonth = new ArrayList<Issuer>();
		
		issuerForLastMonth = getResponse(DataRequestConstructor.getIssuerForLastMonthRequest(secId), issuerForLastMonth.getClass());
		
		return issuerForLastMonth;
	}
	
	@GetMapping("/last-week")
	public List<Issuer> getIssuerForLastWeek(@RequestParam String secId) {
		List<Issuer> issuerForLastWeek = new ArrayList<>();
		
		issuerForLastWeek = getResponse(DataRequestConstructor.getIssuerForLastWeekRequest(secId), issuerForLastWeek.getClass());
		
		return issuerForLastWeek;
	}
	
	@PostMapping("/certain")
	public List<Issuer> getCertainIssuersNow(@RequestBody List<IssuerMetadata> list) {
		
		return DataRequestConstructor.postResponse(list);
	}
}
