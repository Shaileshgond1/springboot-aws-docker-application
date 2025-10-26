package com.flipkart.jwt;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class Jwt {
	
	
	SecretKey secretKey;
	
	public Jwt() throws NoSuchAlgorithmException {
	
		KeyGenerator keyGenerator  =  KeyGenerator.getInstance("HmacSHA256");
		
		         secretKey=  keyGenerator.generateKey();
		         
		         String Sec=Base64.getEncoder().encodeToString(secretKey.getEncoded());
		         System.out.println(Sec);
		
	}

	
	public String generateToken(String username) {
		
		Map<String , Object> claims=new HashMap<>();		
                		
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(Keys.hmacShaKeyFor(secretKey.getEncoded()))
				.compact();			
		
	}
	
	public  SecretKey getKey() {
		  return secretKey;
	  }
	
	
	
	
	

}
