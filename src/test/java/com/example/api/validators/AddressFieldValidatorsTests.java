package com.example.api.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressFieldValidatorsTests {

	@Test
	public void cepValidatorTestTrue() {
		assertThat(AddressFieldsValidators.cepValidator("90480201")).isEqualTo(true);
	}
	
	@Test
	public void cepValidatorTestFalse() {
		assertThat(AddressFieldsValidators.cepValidator("90480")).isEqualTo(false);
	}
	
}
