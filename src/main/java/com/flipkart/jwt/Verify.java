	package com.flipkart.jwt;

import java.util.Collection;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.flipkart.entity.Customer;

@Component
public class Verify {
	
	@Autowired
    AuthenticationManager authmanager;
	
	@Autowired
	Jwt servic;
	
	
	public String verifyUserforToken(Customer emp) {
		
		Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(emp.getUsername(),emp.getPassword()));
		
		System.out.println(emp);
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		     
		Collection<? extends GrantedAuthority> authorities  = userDetails.getAuthorities();
		
		     boolean  isCustomer=    authorities.stream().anyMatch(a->a.getAuthority().equals("ROLE_CUSTOMER"));
		
		     
		     System.out.println("isCustomer :" + isCustomer);
		     
		     if(isCustomer) {
		    	 
		    	 return	servic.generateToken(emp.getUsername());
		    	 
		     }
		     else {
		    	 throw new RuntimeException("Access denied for non-customers");
		     }	
		
	}

}
