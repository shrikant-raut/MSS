package com.security.controller;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.model.Employee;

@RestController
public class EmployeeController {
	
	@RequestMapping(value = "/showEmployee", method = RequestMethod.GET)
	public ModelAndView  showEmployee(@RequestParam("code") String code) throws JsonProcessingException, IOException {
		ResponseEntity<String> response = null;
		System.out.println("Authorization Code------" + code);

		RestTemplate restTemplate = new RestTemplate();

		String credentials = "iphone:secret";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		String access_token_url = "http://localhost:8082/oauth/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:8091/showEmployee";

		response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

		System.out.println("Access Token---------" + response.getBody());

		// Get the Access Token From the received JSON response
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		String token = node.path("access_token").asText();

		String url = "http://localhost:8082/getEmployeesList";

		// Use the access token for authentication
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers1);

		ResponseEntity<Employee[]> employees = restTemplate.exchange(url, HttpMethod.GET, entity, Employee[].class);
		System.out.println(employees);
		Employee[] employeeArray = employees.getBody();

		ModelAndView model = new ModelAndView("showEmployees");
		model.addObject("employees", Arrays.asList(employeeArray));
		return model;
	}

}
