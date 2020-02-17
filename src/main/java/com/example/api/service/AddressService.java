package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.repository.AddressRepository;

import com.example.api.domain.Address;

@Service
public class AddressService {
	
	private AddressRepository repository;
	
	@Autowired
	public AddressService(AddressRepository repository) {
		this.repository = repository;
	}
	
	public List<Address> findAll(){
		return repository.findAllByOrderByCepAsc();
	}

	public Optional<Address> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}
	
	public Address save(Address address) {
		Address save = repository.save(address);
		return save;
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
