package com.example.customer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.customer.entities.Orders;

public interface IOrderService {
	public Orders createOrders(String customerEmail);
	public List<Orders> getAllOrdersByCustomerEmail(String customerEmail);
}
