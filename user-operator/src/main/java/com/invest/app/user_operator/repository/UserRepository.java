package com.invest.app.user_operator.repository;

import com.invest.app.user_operator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmailAndPassword(String email, String password);
	
	User findByName(String name);
}
