package com.example.api.restConsumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.api.domain.Address;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CepApiConsumer {
	
	public static Address getAddressByPostalCode(String postalCode) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("cep", postalCode);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://viacep.com.br/ws/{cep}/json/");
		
		HttpEntity<String> response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri() , HttpMethod.GET, entity, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Address address = new Address();
		try {
			address = objectMapper.readValue(response.getBody(), Address.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return address;
	}
	
}
