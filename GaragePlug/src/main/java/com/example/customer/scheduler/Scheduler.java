package com.example.customer.scheduler;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.customer.entities.Customers;
import com.example.customer.services.ICustomerService;

@Configuration
@EnableScheduling
public class Scheduler {
	
	@Autowired
	private ICustomerService cs;
	
	@Scheduled(cron = "0 37 23 ? * *")
	public void cronJobSch() {
	      
		List<Customers> customers = cs.getAllCustomers();
		
		customers.stream().forEach(customer->{
			int orders = customer.getOrders().size();
			if(orders>=5 && orders<=9)
				System.out.println("Hey "+ customer.getEmail() + ", order "+ (10-orders) +" more items to get 10% additional discount and GOLD membership for free");
			else if(orders>=15 && orders <=19)
				System.out.println("Hey "+ customer.getEmail() + ", order "+ (20-orders) +"more items to get 20% additional discount and PLATINUM membership for free");
		});
	      
	}
	
	
}
