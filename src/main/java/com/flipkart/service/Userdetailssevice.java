package com.flipkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.flipkart.dao.Cust_Dao;
import com.flipkart.entity.Customer;

@Component
public class Userdetailssevice implements UserDetailsService{

	@Autowired
	Cust_Dao dao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Customer customer= dao.findByUsername(username);
		
		if(customer==null) {
			throw new UsernameNotFoundException(username);
		}
		return new Principal(customer);
	}

}
