package com.flipkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.entity.Customer;
import com.flipkart.jwt.Jwt;
import com.flipkart.jwt.Verify;
import com.flipkart.service.Dbservice;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Customer/*")
public class Cust_Controller {

	@Autowired
	Dbservice dbservice;
	
	
	
	@Autowired
	Verify verify;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody Customer cust) {
		
		Customer customer= dbservice.add_Cust(cust);
		if(customer==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.ok(customer);
		
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<List<Customer>> getAll() {
		List<Customer> cust= dbservice.get_Cust();
		if(cust.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.ok(cust);
		
	}
	
	
//	@GetMapping("/token")
//	public Object getToken(HttpServletRequest req) {
//		
//		return req.getAttribute("_csrf");
//	}
	
	
	@PostMapping("/login")
	public Object getToken(@RequestBody Customer cust) {
		
		System.out.println(cust);
		return verify.verifyUserforToken(cust);
		
		
	}
	
	
}
