package com.ClienteApiRestSnider.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetailsModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_detail_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private InvoiceModel InvoiceId;

	@Column(name="amoun")
	private int amoun;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductModel ProductId;

}
