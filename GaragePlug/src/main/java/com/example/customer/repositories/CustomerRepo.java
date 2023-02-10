package com.example.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.customer.entities.Customers;
@Repository
public interface CustomerRepo extends JpaRepository<Customers,Integer>{

	public Customers findByEmail(String email);

}
