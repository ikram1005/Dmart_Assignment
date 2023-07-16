package com.assignment.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.model.Customer;
import com.assignment.repository.CustomerRepo;

@Service
public class CustomeUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Customer> optional=userRepo.findByEmail(username);
		
		if (optional.isPresent()) {
			
			Customer customer=optional.get();
			
			List<GrantedAuthority>authorities =new ArrayList<>();
			SimpleGrantedAuthority sga=new SimpleGrantedAuthority(customer.getRole());
			authorities.add(sga);
			return new User(customer.getEmail(), customer.getPassword(), authorities);
		}
		else {
			throw new BadCredentialsException("Customer not found with this name "+username);
		}
		
	}

}
