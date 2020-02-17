package com.example.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AddressServiceTests {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;

	
	@Before
	public void beforeTests() {
		Customer customer = new Customer(new Long(1), "ramon", "ramon@gmail.com");
		customer = customerService.save(customer);
	
		Address address1 = new Address(new Long(1), "90480201", "Rua Anita Garibaldi","de 1241 ao fim - lado ímpar","Boa Vista", customerService.findById(new Long(1)).get());
		addressService.save(address1);
		Address address2 = new Address(new Long(2), "90010971", "Rua Demétrio Ribeiro 1164 Loja 02","","Centro Histórico", customerService.findById(new Long(1)).get());
		addressService.save(address2);
		Address address3 = new Address(new Long(3), "90020008", "Rua dos Andradas","de 1200 a 1400 - lado par","Centro Histórico", customerService.findById(new Long(1)).get());
		addressService.save(address3);
	}
	
	@Test
	public void saveTest() {
		Address addressToSave = new Address(new Long(4), "90480201", "Rua Anita Garibaldi","de 1241 ao fim - lado ímpar","Boa Vista", customerService.findById(new Long(1)).get());
		assertThat(addressService.save(addressToSave).getCep()).isEqualTo(addressToSave.getCep());
	}
	
	@Test
	public void findAllTest() {
		List<Address> addressList = addressService.findAll();
		assertThat(addressList.size()).isEqualTo(4);
	}
	
	@Test
	public void findByIdTest(){
		Address addressReference = new Address(new Long(1), "90480201", "Rua Anita Garibaldi","de 1241 ao fim - lado ímpar","Boa Vista", customerService.findById(new Long(1)).get());
		Optional<Address> address = addressService.findById(new Long(1));
		assertThat(address.get().getCep()).isEqualTo(addressReference.getCep());
	}
	
	@Test
	public void deleteTest() {
		addressService.deleteById(new Long(3));
		assertThat(addressService.findById(new Long(3)).isPresent()).isEqualTo(false);
	}
	
	
	
}
