package com.ClienteApiRestSnider.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "description")
	private String description;
	@Column(name = "code")
	private String code;
	@Column(name = "stock")
	private int stock;
	@Column(name = "price")
	private double price;

	@Column(name = "active")
	private boolean active;

}
