package com.example.customer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.customer.entities.Customers;
import com.example.customer.entities.Orders;
@Repository
public interface OrderRepo extends JpaRepository<Orders,Integer>{


	List<Orders> findAllOrdersByCustomer(Customers customer);

}
