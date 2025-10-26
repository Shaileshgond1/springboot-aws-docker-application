package com.flipkart.conf;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.flipkart.filter.JwtFilter;
import com.flipkart.service.Userdetailssevice;
import static org.springframework.security.config.Customizer.withDefaults;

@org.springframework.context.annotation.Configuration
public class Configuration {

	
	@Autowired
	Userdetailssevice userdetailssevice;
	@Autowired
	JwtFilter jwtFilter;
	
//	@Bean
//	public SecurityFilterChain   securityFilterChain(HttpSecurity http ) throws Exception {
//		
//		 http
//				.csrf(Customizer->Customizer.disable())
//				.authorizeHttpRequests(req->req.requestMatchers("/login").permitAll().anyRequest().authenticated());
//				.httpBasic(withDefaults())
//				.formLogin(withDefaults())
//				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		 
//		 return http.build();
//		
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(req -> req
	            .requestMatchers("/login").permitAll()
	            .anyRequest().authenticated()
	        )
	        .httpBasic(withDefaults())
	        .formLogin(withDefaults())
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(userdetailssevice);
		
		
		dao.setPasswordEncoder(getEncoder());
		
		
		return dao;
		
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
		
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}
