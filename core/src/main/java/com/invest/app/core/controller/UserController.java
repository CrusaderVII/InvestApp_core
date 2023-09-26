package com.invest.app.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invest.app.core.repository.UserRequestConstructor;
import com.invest.app.data_extract.entities.IssuerMetadata;
import com.invest.app.user_operator.model.User;
import com.invest.app.user_operator.repository.UserService;

import static com.invest.app.core.repository.UserRequestConstructor.getResponse;

@CrossOrigin(originPatterns = "http://localhost:4200/")
@RequestMapping("/investapp.com/user")
@RestController
public class UserController {
	
	@Autowired
	public UserService service;
	
	@GetMapping("/")
	public User getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
		User user = new User();
		user = getResponse(UserRequestConstructor.getUserAuthRequest(password, email), user.getClass());
		
		return user;
	}
	
	@GetMapping("/all") 
	public List<User> getAllUsers () {
		
		return service.getAllUsers();
	}
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		User response = UserRequestConstructor.postResponse(user);
		
		return response;
	}
	
	@GetMapping("issuers/mark")
	public IssuerMetadata bookmarkIssuer(@RequestParam String userName, @RequestParam String secId) {
		IssuerMetadata metadata = new IssuerMetadata();
		metadata = getResponse(UserRequestConstructor.bookmarkIssuerRequest(userName, secId), metadata.getClass());
		
		return metadata;
	}
	
	@GetMapping("issuers")
	public List<IssuerMetadata> getBookmarkedIssuers(@RequestParam String userName) {
		List<IssuerMetadata> metadata = new ArrayList<>();
		metadata = getResponse(UserRequestConstructor.getUsersBookmarksRequest(userName), metadata.getClass());
		
		return metadata;
	}
	
	@DeleteMapping("/issuers/delete")
	public void deleteIssuersFromBookmarks(@RequestParam String userName, @RequestParam String secId) {

		UserRequestConstructor.deleteResponse(userName, secId);
	}
}
