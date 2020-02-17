package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public Customer save(Customer customer) {
		Customer save = repository.save(customer);
		return save;
	}

	public Customer update(Customer customer) {
		// TODO Auto-generated method stub
		Customer save = repository.save(customer);
		return save;
	}

	public List<Customer> getAllPaginated(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Customer> resultPages = repository.findAll(paging);
		
		if(resultPages.hasContent()) {
			return resultPages.getContent();
		}else {
			return new ArrayList<Customer>();
		}
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
}
