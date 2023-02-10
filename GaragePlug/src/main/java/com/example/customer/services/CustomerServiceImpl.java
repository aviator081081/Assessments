package com.example.customer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.entities.CustomerType;
import com.example.customer.entities.Customers;
import com.example.customer.exception.CustomerAlreadyPresentException;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.repositories.CustomerRepo;
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepo cr;
//	@Autowired
//	public CustomerServiceImpl(CustomerRepo cr) {
//		this.cr = cr;
//	}
	
	@Override
	public Customers createCustomer(String email) {
		
		if(cr.findByEmail(email)!=null)
			throw new CustomerAlreadyPresentException();
		Customers customer = new Customers();
		customer.setEmail(email);
		customer.setType(CustomerType.REGULAR);
		try {
		Customers createdCustomer = cr.save(customer);
		return createdCustomer;
		}
		catch(Exception e) {
			throw e;
		}
	}

	@Override
	public List<Customers> getAllCustomers() {
		List<Customers> customersList = cr.findAll();
		return customersList;
	}

	@Override
	public Customers getCustomerByEmail(String email) {
		Customers customer = cr.findByEmail(email);
		if(customer == null)
			throw new CustomerNotFoundException();
		return customer;
	}

	@Override
	public void checkAndPromoteCustomer(Customers customer) {
		int totalOrders = customer.getOrders().size();
		if(totalOrders>=9 && totalOrders<=18)
			customer.setType(CustomerType.GOLD);
		else if(totalOrders>=19)
			customer.setType(CustomerType.PLATINUM);
		
		cr.save(customer);
	
	}


}
