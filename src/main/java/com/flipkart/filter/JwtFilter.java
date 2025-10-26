package com.flipkart.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.flipkart.service.Userdetailssevice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtFilter  extends OncePerRequestFilter{

	@Autowired
	Jwtimpl impl;
	
	@Autowired
	Userdetailssevice userservice;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		

	    String token="";
	    String username="";
	    
	
//        String jwtHeader=    (String) request.getAttribute("Authorization");
        String jwtHeader = request.getHeader("Authorization");
        
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            // No token? Just let the request continue to /login or wherever
            filterChain.doFilter(request, response);
            return;
        }
        
        
        if(jwtHeader!=null && jwtHeader.startsWith("Bearer ")) {
        	
        	token =jwtHeader.substring(7);
        	
        	username=impl.extractUsername(token);
        	
        	
        	System.out.println(token +" :"+ username);
        	
        	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
        		
        		     
        	UserDetails detail=  userservice.loadUserByUsername(username);
        	
        	System.out.println( "token ==>" + impl.isValid(token, detail));
        	
//        	System.out.println( detail.getUsername()+" "+  detail.getPassword() + detail.getAuthorities());
        	
        if(	!impl.isValid(token, detail)) {
        	
        	
        	
        	UsernamePasswordAuthenticationToken tokrn=new UsernamePasswordAuthenticationToken(detail,"",detail.getAuthorities());
        	
        	
        	
        	
        	SecurityContextHolder.getContext().setAuthentication(tokrn);
        	
        }
        
        
        filterChain.doFilter(request, response);
        		
        		
        		
        }
        	
        	
        }
		
	}
        
}
