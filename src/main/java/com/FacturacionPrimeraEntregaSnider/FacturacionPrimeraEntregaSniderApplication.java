package com.FacturacionPrimeraEntregaSnider;

import com.FacturacionPrimeraEntregaSnider.Entities.Client;
import com.FacturacionPrimeraEntregaSnider.Entities.Invoice;
import com.FacturacionPrimeraEntregaSnider.Entities.Product;
import com.FacturacionPrimeraEntregaSnider.Repositories.ClientRepository;
import com.FacturacionPrimeraEntregaSnider.Repositories.InvoiceRepository;
import com.FacturacionPrimeraEntregaSnider.Repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class FacturacionPrimeraEntregaSniderApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(FacturacionPrimeraEntregaSniderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (clientRepository.count() == 0) {
			CreateClientes();
			if(invoiceRepository.count()==0){
				CreateInvoices();
			}
		}
		if (productRepository.count() == 0) {
			CreateProducts();
		}

		var clients = clientRepository.findAll();
		System.out.println("Client report: total " + clients.size() + "\n---------\n ");
		for (Client client : clients) {
			System.out.println(client.toString());
		}
		System.out.println();
		var products = productRepository.findAll();
		System.out.println("Product report: total " + products.size() + "\n---------\n ");
		for (Product product : products) {
			System.out.println(product.toString());
		}
		System.out.println();
		var invoices = invoiceRepository.findAll();
		System.out.println("Invoice report: total " + invoices.size() + "\n---------\n ");
		for (Invoice invoice : invoices) {
			System.out.println(invoice.toString());
		}
		System.out.println();
	}

	private void CreateClientes() {
		List<Client> clients = new ArrayList<>();
		Client client = new Client();
		client.setName("Juan");
		client.setLastName("Perez");
		client.setDocNumber("12345678");
		clients.add(client);

		Client client2 = new Client();
		client2.setName("Pedro");
		client2.setLastName("Gomez");
		client2.setDocNumber("87654321");
		clients.add(client2);

		Client client3 = new Client();
		client3.setName("Maria");
		client3.setLastName("Gonzalez");
		client3.setDocNumber("12345678");
		clients.add(client3);

		Client client4 = new Client();
		client4.setName("Jose");
		client4.setLastName("Lopez");
		client4.setDocNumber("87654321");
		clients.add(client4);

		Client client5 = new Client();
		client5.setName("Ana");
		client5.setLastName("Martinez");
		client5.setDocNumber("12345678");
		clients.add(client5);

		Insert(clientRepository, clients);
	}

	private void CreateProducts() {
		List<Product> products = new ArrayList<>();

		Product product = new Product();
		product.setDescription("Gaseosa cola 1lt");
		product.setCode("12345678");
		product.setStock(10);
		product.setPrice(100.00);
		products.add(product);

		Product product2 = new Product();
		product2.setDescription("Gaseosa lima 1lt");
		product2.setCode("12345679");
		product2.setStock(15);
		product2.setPrice(115.00);
		products.add(product2);

		Product product3 = new Product();
		product3.setDescription("Gaseosa pomelo 1lt");
		product3.setCode("12345680");
		product3.setStock(20);
		product3.setPrice(100.50);
		products.add(product3);

		Product product4 = new Product();
		product4.setDescription("Gaseosa cola 1lt");
		product4.setCode("12345681");
		product4.setStock(10);
		product4.setPrice(100.00);
		products.add(product4);

		Product product5 = new Product();
		product5.setDescription("Papas fritas 1kg");
		product5.setCode("12345510");
		product5.setStock(100);
		product5.setPrice(90.00);
		products.add(product5);

		Product product6 = new Product();
		product6.setDescription("Palitos salados 500g");
		product6.setCode("12345511");
		product6.setStock(50);
		product6.setPrice(30.15);
		products.add(product6);

		Product product7 = new Product();
		product7.setDescription("Dulce de leche 500g");
		product7.setCode("22345650");
		product7.setStock(11);
		product7.setPrice(80.00);
		products.add(product7);

		Product product8 = new Product();
		product8.setDescription("Manteca 500g");
		product8.setCode("22345651");
		product8.setStock(15);
		product8.setPrice(60.00);
		products.add(product8);

		Product product9 = new Product();
		product9.setDescription("Dulce de membrillo 1k");
		product9.setCode("22345652");
		product9.setStock(13);
		product9.setPrice(75.00);
		products.add(product9);

		Insert(productRepository, products);
	}

	private void CreateInvoices(){
		List<Invoice> invoices = new ArrayList<>();

		Date date = new Date();

		Invoice invoice = new Invoice();
		invoice.setClientId(clientRepository.findById(1L).get());
		invoice.setCreatedAt(new Date(date.getTime() + 2 * 24 * 60 * 60 * 1000));
		invoice.setTotal(100.00);
		invoices.add(invoice);

		Invoice invoice2 = new Invoice();
		invoice2.setClientId(clientRepository.findById(1L).get());
		invoice2.setCreatedAt(new Date(date.getTime() + 3 * 24 * 60 * 60 * 1000));
		invoice2.setTotal(200.00);
		invoices.add(invoice2);

		Invoice invoice3 = new Invoice();
		invoice3.setClientId(clientRepository.findById(2L).get());
		invoice3.setCreatedAt(new Date(date.getTime() + 4 * 24 * 60 * 60 * 1000));
		invoice3.setTotal(300.00);
		invoices.add(invoice3);

		Invoice invoice4 = new Invoice();
		invoice4.setClientId(clientRepository.findById(2L).get());
		invoice4.setCreatedAt(new Date(date.getTime() + 5 * 24 * 60 * 60 * 1000));
		invoice4.setTotal(400.00);
		invoices.add(invoice4);

		Invoice invoice5 = new Invoice();
		invoice5.setClientId(clientRepository.findById(3L).get());
		invoice5.setCreatedAt(new Date(date.getTime() + 6 * 24 * 60 * 60 * 1000));
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
