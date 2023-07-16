package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.UserException;
import com.assignment.model.Customer;
import com.assignment.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/hello")
	public String get() {
		return "Welcome";
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> create(@RequestBody Customer customer){
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		Customer createUser = customerService.createUser(customer);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/{email}")
	public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable("email") String email) throws UserException{
	Customer customer= customerService.getCustomerByEmail(email);
	return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
	}
	
	

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAll() throws UserException{
		List<Customer> users=customerService.getAllUser();
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/signIn")
	public ResponseEntity<String> signIn(Authentication authentication) throws UserException{
		
		System.out.println(authentication);
	
		Customer customer=customerService.getCustomerByEmail(authentication.getName());
		
		return new ResponseEntity<>(customer .getName()+" Logged in successfully",HttpStatus.ACCEPTED);
	}
}
