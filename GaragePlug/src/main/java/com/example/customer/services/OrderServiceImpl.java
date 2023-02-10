package com.example.customer.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.entities.Customers;
import com.example.customer.entities.Orders;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.exception.OrderCannotBePlacedException;
import com.example.customer.repositories.OrderRepo;
@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderRepo or;
	
	@Autowired
	private ICustomerService cs;
	
	
	@Override
	public Orders createOrders(String customerEmail) {
		
		Customers customer = null;
		Orders createdOrder = null;
		
		customer = cs.getCustomerByEmail(customerEmail);
		if(customer == null)
			throw new CustomerNotFoundException();
		
		Integer discount = getDiscount(customer);
		
		Orders order = new Orders(LocalDate.now(),discount,customer);
		createdOrder = or.save(order);
		if(createdOrder!=null) {
			cs.checkAndPromoteCustomer(customer);	
		}
		else
			throw new OrderCannotBePlacedException();
		return createdOrder;
	}

	private Integer getDiscount(Customers customer) {
		int totalOrders = customer.getOrders().size();
		if(totalOrders>=9 && totalOrders<=18)
			return 10;
		else if(totalOrders>=19 )
			return 20;
		return 0;
	}

	
	@Override
	public List<Orders> getAllOrdersByCustomerEmail(String customerEmail) {
		
		Customers customer = cs.getCustomerByEmail(customerEmail);
			if(customer == null)
				throw new CustomerNotFoundException();
			
	
		return customer.getOrders();
	}

}
