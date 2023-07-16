package com.assignment.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exception.UserException;
import com.assignment.model.Customer;
import com.assignment.repository.CustomerRepo;
import com.assignment.service.CustomerService;

@Service
public class ServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer createUser(Customer customer) {
		return customerRepo.save(customer);
	}


	@Override
	public List<Customer> getAllUser() throws UserException {
		List<Customer> customers=customerRepo.findAll();
		
		if(!customers.isEmpty())return customers;
		else throw new UserException("Not found users");
	}

	@Override
	public Customer getCustomerByEmail(String email) throws UserException {
		return customerRepo.findByEmail(email).orElseThrow(()->new UserException("email not found "+email));
	}

}
