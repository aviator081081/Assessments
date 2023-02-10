package com.example.customer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.customer.entities.CustomerType;
import com.example.customer.entities.Customers;
import com.example.customer.entities.Orders;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.repositories.OrderRepo;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	@Mock
	private OrderRepo or;

	@Mock
	private ICustomerService cs;
	
	@InjectMocks
	private IOrderService os = new OrderServiceImpl();
	
	int discount = 0;
	String email = "abc@gmail.com";
	private  CustomerType type = CustomerType.REGULAR;
	private List<Orders> orderList = new ArrayList<>();
	
	
	
	@Test
	public void createOrdersReturnsOrder() {
		Customers cust = new Customers(null,email,type,orderList);
		Orders order = new Orders(LocalDate.now(),discount,cust);
		orderList.add(order);
		when(cs.getCustomerByEmail(email)).thenReturn(cust);
		when(or.save(order)).thenReturn(order);
		assertEquals(order,os.createOrders(email));
		
	}
	
	@Test
	public void  createOrderThrowsCustomerNotFoundException() {
		when(cs.getCustomerByEmail(email)).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class,()->os.createOrders(email));
	}
	
	
	private Method getDiscountMethod() throws NoSuchMethodException {
	    Method method = OrderServiceImpl.class.getDeclaredMethod("getDiscount", Customers.class);
	    method.setAccessible(true);
	    return method;
	}
	
	@Test
	public void getDiscount10() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<Orders> orderList = Stream.of(new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders()).toList();
		Customers cust = new Customers(null,email,type,orderList);
		assertEquals(10,getDiscountMethod().invoke(os, cust));
	}
	
	@Test
	public void getDiscount20() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<Orders> orderList = Stream.of(new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders(),new Orders()).toList();
		Customers cust = new Customers(null,email,type,orderList);
		assertEquals(20,getDiscountMethod().invoke(os, cust));
	}
	
	
	
	
}
