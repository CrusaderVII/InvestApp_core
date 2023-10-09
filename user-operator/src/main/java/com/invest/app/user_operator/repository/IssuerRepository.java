package com.invest.app.user_operator.repository;

import com.invest.app.user_operator.model.IssuerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuerRepository extends JpaRepository<IssuerData, Long>{
	
	public IssuerData findBySecId(String secId); 
	
}