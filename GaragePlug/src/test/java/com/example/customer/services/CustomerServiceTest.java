package com.example.customer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.example.customer.entities.CustomerType;
import com.example.customer.entities.Customers;
import com.example.customer.entities.Orders;
import com.example.customer.exception.CustomerAlreadyPresentException;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.repositories.CustomerRepo;

@ExtendWith(MockitoExtension.class)


public class CustomerServiceTest {
	
	@Mock
	private CustomerRepo cr;
	
	@InjectMocks
	private ICustomerService cs = new CustomerServiceImpl();
	
	private  String email = "abc@gmail.com";
	private  CustomerType type = CustomerType.REGULAR;
	private List<Orders> orderList = new ArrayList<>();
	

	@Test
	public void createCustomerReturnsCustomer() {
		Customers cust = new Customers(null,email,type,orderList);
		when(cr.save(cust)).thenReturn(cust);
		assertEquals(cust,cs.createCustomer(email));
	}
	
	
	@Test
	public void CreateCustomerThrowsCustomerAlreadyPresentException() {
		Customers cust = new Customers(null,email,type,orderList);
		when(cr.save(cust)).thenThrow(CustomerAlreadyPresentException.class);
		assertThrows(CustomerAlreadyPresentException.class, ()->cs.createCustomer(email));
	}
	
	@Test
	public void getAllCustomers() {
		Customers cust = new Customers(null,email,type,orderList);
		when(cr.findAll()).thenReturn(  Stream.of(new Customers(),new Customers(),new Customers()).toList()   );
		assertEquals(3,cs.getAllCustomers().size());
	}
	
	@Test
	public void getCustomerByEmailReturnsCustomer() {
		Customers cust = new Customers(null,email,type,orderList);
		when(cr.findByEmail(email)).thenReturn(cust);
		assertEquals(cust,cs.getCustomerByEmail(email));
	}
	
	@Test
	public void getCustomerByEmailThrowsCustomerNotFoundException() {
		
		when(cr.findByEmail(email)).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class,()->cs.getCustomerByEmail(email));
	}
	
	@Test public void checkAndPromoteCustomerForGold() {
		List<Orders> orderList = Stream.of(new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders()).toList();
		Customers cust = new Customers(null,email,type,orderList);
		when(cr.save(cust)).thenReturn(cust);
		cs.checkAndPromoteCustomer(cust);
		assertEquals(CustomerType.GOLD, cust.getType());
	}
	
	@Test public void checkAndPromoteCustomerForPlatinum() {
		List<Orders> orderList = Stream.of(new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders()).toList();
		Customers cust = new Customers(null,email,type,orderList);
		when(cr.save(cust)).thenReturn(cust);
		cs.checkAndPromoteCustomer(cust);
		assertEquals(CustomerType.PLATINUM, cust.getType());
	}

}
