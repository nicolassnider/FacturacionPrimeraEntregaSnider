package com.ClienteApiRestSnider.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetailsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_details_id")
	private Long id;

	@Positive(message = "El precio debe ser mayor a 0")
	@Column(name = "amoun")
	private int amoun;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private ProductModel productId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id")
	@JsonBackReference
	private InvoiceModel invoice;

	@Positive(message = "El precio debe ser mayor a 0")
	@Column(name = "unit_price")
	private double unitPrice;

}
