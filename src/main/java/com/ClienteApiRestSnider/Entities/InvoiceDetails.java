package com.ClienteApiRestSnider.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails {

	// constructor vacío
	public InvoiceDetails() {
		super();
	}

	// Columnas

	// PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_detail_id")
	private Long Id;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private Invoice InvoiceId;

	@Column(name="amoun")
	private int Amoun;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product ProductId;



	// getters & setters
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Invoice getInvoiceId() {
		return InvoiceId;
	}

	public void setInvoiceId(Invoice invoiceId) {
		InvoiceId = invoiceId;
	}

	public Product getProductId() {
		return ProductId;
	}

	public void setProductId(Product productId) {
		ProductId = productId;
	}

	//override toString hashcode y equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		InvoiceDetails other = (InvoiceDetails) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvoiceDetails [Id=" + Id + ", InvoiceId=" + InvoiceId + ", ProductId=" + ProductId + "]";
	}
}
