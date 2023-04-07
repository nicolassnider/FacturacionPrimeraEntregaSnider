package com.ClienteApiRestSnider.DTO;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Entities.InvoiceDetailsModel;
import com.ClienteApiRestSnider.Entities.InvoiceModel;
import com.ClienteApiRestSnider.Entities.ProductModel;
import org.modelmapper.ModelMapper;

public class GenericModelMapper {

	private final ModelMapper mapper = new ModelMapper();
	private static GenericModelMapper instance;

	private GenericModelMapper() {
	}

	// patrón singleton
	public static GenericModelMapper singleInstance() {
		if (instance == null) {
			instance = new GenericModelMapper();
		}
		return instance;
	}

	public ClientDTO mapToClientDTO(ClientModel client) {
		return mapper.map(client, ClientDTO.class);
	}

	public ClientModel mapToClientModel(ClientDTO client) {
		return mapper.map(client, ClientModel.class);
	}

	public InvoiceDetailsDTO mapToInvoiceDetailsDTO(InvoiceDetailsModel invoiceDetails) {
		return mapper.map(invoiceDetails, InvoiceDetailsDTO.class);
	}

	public InvoiceDetailsModel mapToInvoiceDetailsModel(InvoiceDetailsDTO invoiceDetails) {
		return mapper.map(invoiceDetails, InvoiceDetailsModel.class);
	}

	public InvoiceDTO maptToInvoiceDTO(InvoiceModel invoice) {
		return mapper.map(invoice, InvoiceDTO.class);
	}

	public InvoiceModel mapToInvoiceModel(InvoiceDTO invoice) {
		return mapper.map(invoice, InvoiceModel.class);
	}

	public ProductDTO mapToProductDTO(ProductModel product) {
		return mapper.map(product, ProductDTO.class);
	}

	public ProductModel mapToProductModel(ProductDTO product) {
		return mapper.map(product, ProductModel.class);
	}
}
