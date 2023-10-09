package com.invest.app.user_operator.controller;

import com.invest.app.user_operator.model.IssuerData;
import com.invest.app.user_operator.model.User;
import com.invest.app.user_operator.repository.IssuerRepository;
import com.invest.app.user_operator.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users/get")
public class GetController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	IssuerRepository issuerRepository;
	
	@GetMapping("/user/id")
	public User getUserById(@RequestParam Long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/user") 
	public User getUser(@RequestParam String email, @RequestParam String password) {
		User user = userService.getUserByEmailAndPassword(email, password);
		
		return user;
	}
	
	@GetMapping("/user/issuers")
	public List<IssuerData> getIssuerDatasOfUser(@RequestParam Long id) {
		User user = userService.getUserById(id);
		
		return user.getIssuersDatas();
	}
	
	@GetMapping("/issuer")
	public IssuerData getIssuer(@RequestParam Long id) {
		return issuerRepository.findById(id).get();
	}
}
