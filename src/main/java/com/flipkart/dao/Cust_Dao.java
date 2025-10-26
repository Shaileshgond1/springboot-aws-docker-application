package com.flipkart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.flipkart.entity.Customer;

@Component
public interface Cust_Dao extends JpaRepository<Customer, Integer>{

	Customer findByUsername(String username);

}
