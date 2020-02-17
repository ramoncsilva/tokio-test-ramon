package com.example.api.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "name",nullable = false)
	@NotEmpty
	private String name;

	@Column(name = "email",nullable = false)
	@NotEmpty
	@Email
	private String email;
	
	@OneToMany(targetEntity=Address.class, mappedBy="customer",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Address> addresses;

	public Customer(final Long id, final String name, final String email) {
		this.name = name;
		this.email = email;
		this.id = id;
	}
	
	public Customer(final String name, final String email) {
		this.name = name;
		this.email = email;
	}
	
	public Customer() {
		
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

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	

}
