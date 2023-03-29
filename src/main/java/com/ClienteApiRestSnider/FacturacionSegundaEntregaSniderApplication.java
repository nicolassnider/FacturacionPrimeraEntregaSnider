package com.ClienteApiRestSnider;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Entities.InvoiceModel;
import com.ClienteApiRestSnider.Entities.ProductModel;
import com.ClienteApiRestSnider.Repositories.ClientRepository;
import com.ClienteApiRestSnider.Repositories.InvoiceDetailsRepository;
import com.ClienteApiRestSnider.Repositories.InvoiceRepository;
import com.ClienteApiRestSnider.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FacturacionSegundaEntregaSniderApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(FacturacionSegundaEntregaSniderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (clientRepository.count() == 0) {
			CreateClientes();
			if (invoiceRepository.count() == 0) {
				CreateInvoices();
			}
		}
		if (productRepository.count() == 0) {
			CreateProducts();
		}

		var clients = clientRepository.findAll();
		System.out.println("Client report: total " + clients.size() + "\n---------\n ");
		for (ClientModel client : clients) {
			System.out.println(client.toString());
		}
		System.out.println();
		var products = productRepository.findAll();
		System.out.println("Product report: total " + products.size() + "\n---------\n ");
		for (ProductModel product : products) {
			System.out.println(product.toString());
		}
		System.out.println();
		var invoices = invoiceRepository.findAll();
		System.out.println("Invoice report: total " + invoices.size() + "\n---------\n ");
		for (InvoiceModel invoice : invoices) {
			System.out.println(invoice.toString());
		}
		System.out.println();
	}

	private void CreateClientes() {
		List<ClientModel> clients = new ArrayList<>();
		ClientModel client = new ClientModel();
		client.setName("Juan");
		client.setLastName("Perez");
		client.setDocNumber("12345678");
		clients.add(client);

		ClientModel client2 = new ClientModel();
		client2.setName("Pedro");
		client2.setLastName("Gomez");
		client2.setDocNumber("12345679");
		clients.add(client2);

		ClientModel client3 = new ClientModel();
		client3.setName("Maria");
		client3.setLastName("Gonzalez");
		client3.setDocNumber("12345680");
		clients.add(client3);

		ClientModel client4 = new ClientModel();
		client4.setName("Jose");
		client4.setLastName("Lopez");
		client4.setDocNumber("12345681");
		clients.add(client4);

		ClientModel client5 = new ClientModel();
		client5.setName("Ana");
		client5.setLastName("Martinez");
		client5.setDocNumber("12345682");
		clients.add(client5);

		Insert(clientRepository, clients);
	}

	private void CreateProducts() {
		List<ProductModel> products = new ArrayList<>();

		ProductModel product = new ProductModel();
		product.setDescription("Gaseosa cola 1lt");
		product.setCode("12345678");
		product.setStock(10);
		product.setPrice(100.00);
		products.add(product);

		ProductModel product2 = new ProductModel();
		product2.setDescription("Gaseosa lima 1lt");
		product2.setCode("12345679");
		product2.setStock(15);
		product2.setPrice(115.00);
		products.add(product2);

		ProductModel product3 = new ProductModel();
		product3.setDescription("Gaseosa pomelo 1lt");
		product3.setCode("12345680");
		product3.setStock(20);
		product3.setPrice(100.50);
		products.add(product3);

		ProductModel product4 = new ProductModel();
		product4.setDescription("Gaseosa cola 1lt");
		product4.setCode("12345681");
		product4.setStock(10);
		product4.setPrice(100.00);
		products.add(product4);

		ProductModel product5 = new ProductModel();
		product5.setDescription("Papas fritas 1kg");
		product5.setCode("12345510");
		product5.setStock(100);
		product5.setPrice(90.00);
		products.add(product5);

		ProductModel product6 = new ProductModel();
		product6.setDescription("Palitos salados 500g");
		product6.setCode("12345511");
		product6.setStock(50);
		product6.setPrice(30.15);
		products.add(product6);

		ProductModel product7 = new ProductModel();
		product7.setDescription("Dulce de leche 500g");
		product7.setCode("22345650");
		product7.setStock(11);
		product7.setPrice(80.00);
		products.add(product7);

		ProductModel product8 = new ProductModel();
		product8.setDescription("Manteca 500g");
		product8.setCode("22345651");
		product8.setStock(15);
		product8.setPrice(60.00);
		products.add(product8);

		ProductModel product9 = new ProductModel();
		product9.setDescription("Dulce de membrillo 1k");
		product9.setCode("22345652");
		product9.setStock(13);
		product9.setPrice(75.00);
		products.add(product9);

		Insert(productRepository, products);
	}

	private void CreateInvoices() {
		List<InvoiceModel> invoices = new ArrayList<>();

		LocalDate date = LocalDate.now();

		InvoiceModel invoice = new InvoiceModel();
		invoice.setClientId(clientRepository.findById(1L).get());
		invoice.setCreatedAt(date);
		invoice.setTotal(100.00);
		invoices.add(invoice);

		InvoiceModel invoice2 = new InvoiceModel();
		invoice2.setClientId(clientRepository.findById(1L).get());
		invoice2.setCreatedAt(date);
		invoice2.setTotal(200.00);
		invoices.add(invoice2);

		InvoiceModel invoice3 = new InvoiceModel();
		invoice3.setClientId(clientRepository.findById(2L).get());
		invoice3.setCreatedAt(date);
		invoice3.setTotal(300.00);
		invoices.add(invoice3);

		InvoiceModel invoice4 = new InvoiceModel();
		invoice4.setClientId(clientRepository.findById(2L).get());
		invoice4.setCreatedAt(date);
		invoice4.setTotal(400.00);
		invoices.add(invoice4);

		InvoiceModel invoice5 = new InvoiceModel();
		invoice5.setClientId(clientRepository.findById(3L).get());
		invoice5.setCreatedAt(date);
		invoice5.setTotal(500.00);
		invoices.add(invoice5);

		Insert(invoiceRepository, invoices);
	}

	private <T> void Insert(JpaRepository repository, List<T> objects) {
		for (Object object : objects) {
			repository.save(object);
		}
	}
}