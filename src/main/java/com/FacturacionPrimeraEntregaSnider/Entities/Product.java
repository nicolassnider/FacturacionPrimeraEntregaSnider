package com.FacturacionPrimeraEntregaSnider.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	// constructor vacío
	public Product() {
		super();
	}

	// Columnas

	// PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@Column(name = "description")
	private String Description;
	@Column(name = "code")
	private String Code;
	@Column(name = "stock")
	private int Stock;
	@Column(name = "price")
	private double Price;

	// getters & setters
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	//override toString hashcode y equals

	@Override
	public String toString() {
		return "Product [Id=" + Id + ", Description=" + Description + ", Code=" + Code + ", Stock=" + Stock + ", Price="
				+ Price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Code == null) ? 0 : Code.hashCode());
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(Price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Stock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Code == null) {
			if (other.Code != null)
				return false;
		} else if (!Code.equals(other.Code))
			return false;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (Double.doubleToLongBits(Price) != Double.doubleToLongBits(other.Price))
			return false;
		if (Stock != other.Stock)
			return false;
		return true;
	}

}
