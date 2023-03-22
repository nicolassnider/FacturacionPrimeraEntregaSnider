package com.FacturacionPrimeraEntregaSnider.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class Client {
	// constructor vacío
	public Client() {
		super();
	}

	// Columnas
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@Column(name = "name")
	private String Name;

	@Column(name = "lastname")
	private String LastName;

	@Column(name = "docnumber")
	private String DocNumber;
}
