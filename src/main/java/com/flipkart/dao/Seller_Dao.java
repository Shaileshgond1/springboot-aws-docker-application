package com.flipkart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.flipkart.entity.Seller;

@Component
public interface Seller_Dao extends JpaRepository<Seller, Integer>{

}
