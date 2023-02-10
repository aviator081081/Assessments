package com.example.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.entities.Customers;
import com.example.customer.exception.CustomerAlreadyPresentException;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.services.ICustomerService;

@RestController
@RequestMapping("/customer")

public class CustomerController {
	
	@Autowired
	private ICustomerService cs;
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customers> createCustomer(@RequestParam String email){
		
		Customers createdCustomer = cs.createCustomer(email);
		return ResponseEntity.ok(createdCustomer);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customers>> getAllCustomers(){
		return ResponseEntity.ok(cs.getAllCustomers());
	} 
	
	@GetMapping("/getCustomerByEmail")
	public ResponseEntity<Customers> getCustomerByEmail(@RequestParam String email){
		Customers customer = cs.getCustomerByEmail(email);
		if(customer==null)
			throw new CustomerNotFoundException();
		return ResponseEntity.ok(customer);
	}

}
