package com.flipkart.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.flipkart.entity.Customer;
@Component
public class Principal implements UserDetails {
 
	Customer cust;
	
	
	
	public Principal(Customer cust) {
		this.cust=cust;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+cust.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return cust.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return cust.getUsername();
	}

}
