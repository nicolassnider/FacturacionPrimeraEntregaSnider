package com.ClienteApiRestSnider.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InvoiceDTO {
	private Long id;
	private LocalDate createdAt;
	private double total;
	private ClientDTO client;
	private List<InvoiceDetailsDTO> invoiceDetails;
}
