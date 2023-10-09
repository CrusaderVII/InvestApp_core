package com.invest.app.user_operator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invest.app.user_operator.model.IssuerData;

public interface IssuerRepository extends JpaRepository<IssuerData, Long>{
	
	public IssuerData findBySecId(String secId); 
	
}