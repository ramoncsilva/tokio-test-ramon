package com.example.api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTests {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Before
	public void prepareDataToTest(){
		Customer customer = new Customer(new Long(1),"ramon", "ramon@gmail.com");
		customer = customerRepository.save(customer);
	
		Address address = new Address(new Long(1), "90480201", "","","", customerRepository.findById(new Long(1)).get());
		addressRepository.save(address);
	}
	
	@Test
    public void saveAdress() {
		
		Address address = new Address(new Long(2),"90480201", "","","", customerRepository.findById(new Long(1)).get());
		Address savedAddres = addressRepository.save(address);
		assertThat(address.getCep()).isEqualTo(savedAddres.getCep());
		
	}
	
	@Test
    public void getAdress() {
		
		Address address = addressRepository.findById(new Long(1)).get();
		assertThat(address.getCep()).isEqualTo("90480201");
		
	}
}
