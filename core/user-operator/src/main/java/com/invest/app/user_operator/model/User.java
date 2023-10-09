package com.invest.app.user_operator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "User")
@Table(name = "users")
public class User {

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
	)
	@Column(
			name = "id",
			updatable = false
	)
	private Long id;
	
	@Column(
			name = "name",
			nullable = false,
			columnDefinition = "TEXT",
			unique = true
	)
	private String name;
	
	@Column(
			name = "email",
			nullable = false,
			columnDefinition = "TEXT",
			unique = true
	)
	private String email;
	
	@Column(
			name = "password",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String password;
	
	@ManyToMany
	@JoinTable(
			name = "user_issuer",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "issuer_id")
	)
	
	@JsonIgnore
	private List<IssuerData> issuersDatas;
	
	public User() {
		
	}
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public void addIssuer(IssuerData issuerData) {
		if (issuersDatas == null) {
			this.issuersDatas = new ArrayList<>();
		}
		
		this.issuersDatas.add(issuerData);
	}
	
	public void deleteIssuer(IssuerData issuerData) {
		if (issuerData == null) return;
		
		issuersDatas.remove(issuerData); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<IssuerData> getIssuersDatas() {
		return issuersDatas;
	}

	public void setIssuersDatas(List<IssuerData> issuersDatas) {
		this.issuersDatas = issuersDatas;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
