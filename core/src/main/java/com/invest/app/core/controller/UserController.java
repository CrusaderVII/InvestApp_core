package com.invest.app.core.controller;

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

import com.invest.app.user_operator.model.User;
import com.invest.app.user_operator.repository.UserService;
import com.invest.app.user_operator.repository.UserRepository;

@CrossOrigin(originPatterns = "http://localhost:4200/")
@RequestMapping("/investapp.com/user")
@RestController
public class UserController {
	
	@Autowired
	public UserService service;
	
	@GetMapping("/")
	public User getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
		
		return service.getUserByEmailAndPassword(email, password);
	}
	
	@GetMapping("/id")
	public User getUserById(@RequestParam Long id) {
		
		return service.getUserById(id);
	}
	
	@GetMapping("/all") 
	public List<User> getAllUsers () {
		
		return service.getAllUsers();
	}
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		
		return service.saveUser(user);
	}
	 
	@DeleteMapping("/delete") 
	public void  deleteUser(@RequestParam Long id) {
		
		service.deleteUser(id);
	}
}
