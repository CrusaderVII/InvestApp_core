package com.invest.app.user_operator.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invest.app.user_operator.model.IssuerData;
import com.invest.app.user_operator.model.User;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IssuerRepository issuerRepository;
	
	public UserService() {
		
	}
	
	public User getUserById(Long id) {
		User user = userRepository.findById(id).get();
		if (user==null) user = new User("not found", "", "");
		
		return user;
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		if (user==null) user = new User("not found", "", "");
		
		return user;
	}
	
	public User getUserByName(String name) {
		User user = userRepository.findByName(name);
		if(user==null) user = new User("not found", "", "");
		
		return user;
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public IssuerData saveIssuerToUser(String userName, String secId) {
		User user = userRepository.findByName(userName);
		IssuerData issuerData = issuerRepository.findBySecId(secId);
		user.addIssuer(issuerData);
		
		userRepository.save(user);
		
		return issuerData;
	}
	
	public void deleteIssuerFromUser(String userName, String secId) {
		User user = getUserByName(userName);
		IssuerData issuerData = getIssuerDataBySecId(secId);
		
		user.deleteIssuer(issuerData);
		
		saveUser(user);
	}
	
	public IssuerData getIssuerDataBySecId(String secId) {
		return issuerRepository.findBySecId(secId);
	}
	
}
