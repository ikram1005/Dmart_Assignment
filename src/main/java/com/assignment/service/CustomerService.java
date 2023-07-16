package com.assignment.service;

import java.util.List;

import com.assignment.exception.UserException;
import com.assignment.model.Customer;

public interface CustomerService {
	
	Customer createUser(Customer customer);
	
	List<Customer> getAllUser() throws UserException;
	
	Customer getCustomerByEmail(String email)throws UserException;

}
