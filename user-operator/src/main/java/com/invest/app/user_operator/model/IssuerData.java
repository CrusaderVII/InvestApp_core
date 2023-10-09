package com.invest.app.user_operator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "IssuerData")
@Table(name = "issuers")
public class IssuerData {

	@Id
	@SequenceGenerator(
			name = "issuer_data_sequence",
			sequenceName = "issuer_data_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "issuer_data_sequence"
	)
	@Column(
			name = "id",
			updatable = false
	)
	@JsonIgnore
	private Long id;
	
	@Column(
			name = "sec_id",
			nullable = false
	)
	private String secId;
	
	@Column(
			name = "full_name",
			nullable = false
	)
	private String fullName;
	
	@ManyToMany(
			mappedBy = "issuersDatas"
	)
	@JsonIgnore
	private List<User> users;
	
	public IssuerData() {

	}
		
	public IssuerData(String secId, String fullName) {
		this.secId = secId;
		this.fullName = fullName;
	}
	
	public void addUser(User user) {
		if (users == null) {
			this.users = new ArrayList<>();
		}
		
		this.users.add(user);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "IssuerData [id=" + id + ", secId=" + secId + ", fullName=" + fullName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(secId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssuerData other = (IssuerData) obj;
		return Objects.equals(secId, other.secId);
	}

	
	
}
