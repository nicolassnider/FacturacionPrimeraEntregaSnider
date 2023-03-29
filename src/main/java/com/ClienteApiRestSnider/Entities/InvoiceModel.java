package com.ClienteApiRestSnider.Entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "invoices")
public class InvoiceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "created_at")
	private LocalDate createdAt;

	@Positive(message = "El total debe ser mayor a 0")
	@Column(name = "total")
	private double total;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientModel ClientId;

}
