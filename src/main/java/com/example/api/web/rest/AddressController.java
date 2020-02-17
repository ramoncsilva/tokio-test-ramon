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

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.restConsumer.CepApiConsumer;
import com.example.api.service.AddressService;
import com.example.api.validators.AddressFieldsValidators;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	private AddressService service;
	
	@Autowired
	public AddressController(AddressService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Address> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Address findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}
	
	@RequestMapping(
            value = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> save(@RequestBody Address address) {
		
		if(!AddressFieldsValidators.cepValidator(address.getCep()))
			return new ResponseEntity<Address>(HttpStatus.BAD_REQUEST);
		
		Address requestedAddressByCep = CepApiConsumer.getAddressByPostalCode(address.getCep());
		address.setBairro(requestedAddressByCep.getBairro());
		address.setLocalidade(requestedAddressByCep.getLocalidade());
		address.setLogradouro(requestedAddressByCep.getLogradouro());
		address.setUf(requestedAddressByCep.getUf());
		address.setComplemento(requestedAddressByCep.getComplemento());
		Address saved = service.save(address);
		return new ResponseEntity<Address>(saved, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteById(Long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}catch(IllegalArgumentException ex){
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}catch(EmptyResultDataAccessException ex) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}
	
}
