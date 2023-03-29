package com.ClienteApiRestSnider.Entities;

import org.aspectj.lang.annotation.RequiredTypes;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Length(min = 5, max = 50 ,message = "La descripción debe tener al menos 5 caracteres y no más de 50")
	@Column(name = "description")
	private String description;

	@Length(min = 6, max = 6 ,message = "El código debe tener 6 caracteres")
	@Column(name = "code" , unique = true)
	private String code;
	
	@PositiveOrZero(message = "El stock debe ser mayor o igual a 0")
	@Column(name = "stock")
	private int stock;

	@Positive(message = "El precio debe ser mayor a 0")
	@Column(name = "price")
	private double price;
	
	@Column(name = "active")
	private boolean active;

}
