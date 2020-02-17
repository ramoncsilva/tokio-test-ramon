package com.example.api.validators;

import java.util.regex.Pattern;

public final class AddressFieldsValidators {
	
	public static boolean cepValidator(String cep) {
		
		final Pattern pattern = Pattern.compile("^[0-9]{8}$");
		if(pattern.matcher(cep).matches()) {
			return true;
		}else {
			return false;
		}
	}
	
}
