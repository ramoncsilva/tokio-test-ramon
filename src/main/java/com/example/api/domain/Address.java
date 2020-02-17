package com.example.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "cep",nullable = false)
	@NotNull
	private String cep;
	
	@Column(name = "logradouro",nullable = true)
	private String logradouro;

	@Column(name = "complemento",nullable = true)
	private String complemento;
	
	@Column(name = "bairro",nullable = true)
	private String bairro;
	
	@Column(name = "localidade",nullable = true)
	private String localidade;
	
	@Column(name = "uf",nullable = true)
	private String uf;
	
	@ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable=true)
	private Customer customer;
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public Long getCustomer() {
		return customer.getId();
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Address(Long id, String cep, String logradouro, String complemento, String bairro, Customer customer) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.customer = customer;
	}
	
	public Address() {
		
	}

	public Long getId() {
		return id;
	}
	
	
}
