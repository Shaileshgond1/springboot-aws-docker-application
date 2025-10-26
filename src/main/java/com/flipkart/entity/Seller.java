package com.flipkart.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Seller {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		int id;
		String email;
		String username;
	    String password;
	    String role;
		public Seller(String email, String username, String password, String role) {
			super();
			this.email = email;
			this.username = username;
			this.password = password;
			this.role = role;
		}
		public Seller() {
			super();
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		@Override
		public String toString() {
			return "Customer [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
					+ ", role=" + role + "]";
		}
	    
}
