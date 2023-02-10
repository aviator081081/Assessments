package com.example.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.entities.Orders;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.exception.OrderCannotBePlacedException;
import com.example.customer.services.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderService os;

	@PostMapping("/placeOrder")
	public ResponseEntity<Orders> placeOrder(@RequestParam String email){
		Orders order = os.createOrders(email);
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/getOrdersByCustomerEmail")
	public ResponseEntity<List<Orders>> getOrdersByCustomerEmail(@RequestParam String email){
		List<Orders> orders = os.getAllOrdersByCustomerEmail(email);
		return ResponseEntity.ok(orders);
	} 
}
