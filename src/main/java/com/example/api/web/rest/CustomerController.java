package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}
	
	@GetMapping("/page/{page}")
	public List<Customer> getPage(@PathVariable Integer page) {
		return service.getAllPaginated(page, 2, "name");
	}
	
	@RequestMapping(
            value = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		Customer saved= service.save(customer);         
        return new ResponseEntity<Customer>(saved, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}catch(IllegalArgumentException ex){
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}catch(EmptyResultDataAccessException ex) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
