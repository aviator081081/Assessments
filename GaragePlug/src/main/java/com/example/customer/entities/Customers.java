package com.example.customer.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customer_id;
	
	
	@Email
	@Column(name="email",nullable=false,unique=true)
	private String email;

	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CustomerType type;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Orders> orders=new ArrayList<>();
	
	
	public List<Orders> getOrders(){
		return new ArrayList<Orders>(this.orders);
	}
}
