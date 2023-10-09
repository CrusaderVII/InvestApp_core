package com.invest.app.user_operator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.user_operator.repository.IssuerRepository;
import com.invest.app.user_operator.repository.RequestConstructor;
import com.invest.app.user_operator.repository.UserRepository;
import com.invest.app.user_operator.repository.UserService;
import com.invest.app.user_operator.model.IssuerMetadata;
import com.invest.app.user_operator.model.IssuerData;
import com.invest.app.user_operator.model.User;

@RestController
@RequestMapping("api/user")
public class UserMainController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IssuerRepository issuerRepository;
	
	@GetMapping("/")
	public User authentificationUser(@RequestParam String email, @RequestParam String password) {
		
		return userService.getUserByEmailAndPassword(email, password);
	}
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		
		return userService.saveUser(user);
	}
	
	@GetMapping("/issuers") 
	public List<IssuerData> getUsersBookmarks(@RequestParam String userName) {
		
		return userService.getUserByName(userName)
				.getIssuersDatas();
		
	}
	
	@GetMapping("save/all-issuers")
	public void saveAllIssuers() {	
		List<IssuerMetadata> list = new ArrayList<>();
		
		list = RequestConstructor.getAllIssuersMetadataResponse(list.getClass());
		
		list.stream()
			.forEach(x -> issuerRepository
					.save(new IssuerData(x.getSecId(), x.getFullName())));
	}
	
	@GetMapping("add/issuer")
	public void addIssuerToUser(@RequestParam String userName,
								@RequestParam String secId) {
		
		userService.saveIssuerToUser(userName, secId);
	}
	
	@DeleteMapping("/delete/issuer")
	public void deleteIssuerFromUser(@RequestParam String userName, @RequestParam String secId) {
		
		userService.deleteIssuerFromUser(userName, secId);
	}
}
