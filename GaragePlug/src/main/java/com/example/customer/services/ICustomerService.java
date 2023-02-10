package com.example.customer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.customer.entities.Customers;

public interface ICustomerService {
	public Customers createCustomer(String email);
	public List<Customers> getAllCustomers();
	public  Customers getCustomerByEmail(String email);
	public void checkAndPromoteCustomer(Customers customer);
}
