package com.flipkart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flipkart.dao.Cust_Dao;
import com.flipkart.entity.Customer;
import com.flipkart.entity.Seller;

@Service
public class Dbservice {

	@Autowired
	Cust_Dao dao;
	
	public Customer add_Cust(Customer cust) {
		
		BCryptPasswordEncoder encoder=new  BCryptPasswordEncoder(12);
		
		cust.setPassword(encoder.encode(cust.getPassword()));
		
		return dao.save(cust);
	}
	
	
     public List<Customer> get_Cust() {
		
		return dao.findAll();
    	 
    	
	}
	
	
	
	
	
}
