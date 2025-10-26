package com.flipkart.filter;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.flipkart.jwt.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class Jwtimpl {

	@Autowired
	Jwt secret;
	
	public String extractUsername(String token) {
		
		return 	extractClaims(token,Claims::getSubject);
			
			
		}
	
	
	public Date expirationDate(String token) {
		
	return 	extractClaims(token,Claims::getExpiration);
		
		
	}
	
	public   <T> T  extractClaims(String token , Function<Claims,T> resol) { 
           Claims claim=		extractAllClaims(token);
           
           
           return resol.apply(claim);
           
           
	}
	
	public  Claims  extractAllClaims(String token) {
		
		return Jwts.parser().verifyWith(secret.getKey()).build().parseSignedClaims(token).getPayload();
		 
		
	}
	
	
	public boolean isValid(String token,UserDetails userDetails) {
		return expirationDate(token).before(new Date()) && extractUsername(token).equals(userDetails.getUsername());
	}
	
	
}
