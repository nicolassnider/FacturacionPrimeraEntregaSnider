package com.FacturacionPrimeraEntregaSnider.Entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {
	// constructor vacío
	public Invoice() {
		super();
	}

	// Columnas

	// PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@Column(name = "created_at")
	private Date CreatedAt;

	@Column(name = "total")
	private double Total;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client ClientId;

	// getters & setters
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public Client getClientId() {
		return ClientId;
	}

	public void setClientId(Client clientId) {
		ClientId = clientId;
	}

	// override toString hashcode y equals
	@Override
	public String toString() {
		return "Invoice [Id=" + Id + ", CreatedAt=" + CreatedAt + ", Total=" + Total + ", ClientId=" + ClientId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ClientId == null) ? 0 : ClientId.hashCode());
		result = prime * result + ((CreatedAt == null) ? 0 : CreatedAt.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(Total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Invoice other = (Invoice) obj;
		if (ClientId == null) {
			if (other.ClientId != null)
				return false;
		} else if (!ClientId.equals(other.ClientId))
			return false;
		if (CreatedAt == null) {
			if (other.CreatedAt != null)
				return false;
		} else if (!CreatedAt.equals(other.CreatedAt))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (Double.doubleToLongBits(Total) != Double.doubleToLongBits(other.Total))
			return false;
		return true;
	}

}
