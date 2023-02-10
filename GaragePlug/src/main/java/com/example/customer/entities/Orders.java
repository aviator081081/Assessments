package com.example.customer.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Orders {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orders_id;
	
	@Column(name="creation_time",nullable=false)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDate creationTime;
	
	@Column(name="discount",nullable=false)
	private Integer discount;
	
	@JsonIgnore
	@ManyToOne
	private Customers customer;

	public Orders(LocalDate creationTime, Integer discount, Customers customer) {
		super();
		this.creationTime = creationTime;
		this.discount = discount;
		this.customer = customer;
	}
	
	

}
