package com.example.customer.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.customer.entities.CustomerType;
import com.example.customer.entities.Customers;
import com.example.customer.repositories.CustomerRepo;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerRepoTest {

	@Autowired
	private  CustomerRepo cr;
	

	private  String email = "abc@gmail.com";
	private  CustomerType type = CustomerType.REGULAR;
	private  Customers customer = new Customers();
	
	
	@BeforeAll
	public  void setUp() {
		customer.setEmail(email);
		customer.setType(type);
		System.out.println(cr.save(customer));
	}
	
	@Test
	public void findByEmailTest() {
		
		Customers customer2 = cr.findByEmail(email);
		assertEquals(email, customer2.getEmail());
		assertEquals(type, customer2.getType());
		assertEquals(0, customer2.getOrders().size());
	}
	
	@Test public void findbyEmailReturnsNull() {
		assertNull( cr.findByEmail("dummy@gmail.com"));
	}
	
	@AfterAll
	public  void tearDown() {
		cr.deleteAll();
	}
}
