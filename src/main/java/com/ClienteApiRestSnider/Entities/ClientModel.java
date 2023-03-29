package com.ClienteApiRestSnider.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "clients")
public class ClientModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Size(min = 2, max = 20, message = "El nombre debe tener al menos 5 caracteres y no más de 20")
	@Column(name = "name")
	private String name;

	@Size(min = 2, max = 20, message = "El apellido debe tener al menos 5 caracteres y no más de 20")
	@Column(name = "lastname")
	private String lastName;
	@Size(min = 8, max = 15, message = "El número de documento debe tener al menos 8 caracteres y no más de 15")
	@Column(name = "docnumber", unique = true)
	private String docNumber;

	@Column(name = "active")
	private boolean active = true;
}
