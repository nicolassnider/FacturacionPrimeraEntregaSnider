package com.ClienteApiRestSnider;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Entities.InvoiceDetailsModel;
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
import java.util.Optional;

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
		if (productRepository.count() == 0) {
			CreateProducts();
		}
		if (clientRepository.count() == 0) {
			CreateClientes();
		}
		if (invoiceRepository.count() == 0) {
			CreateInvoices();
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
		/*for (InvoiceModel invoice : invoices) {
			System.out.println(invoice.toString());
		}*/
		System.out.println();
	}

	private void CreateClientes() {
		List<ClientModel> clients = new ArrayList<>();
		ClientModel client = new ClientModel();
		client.setName("Juan");
		client.setLastName("Perez");
		client.setDocNumber("1234567890");
		clients.add(client);

		ClientModel client2 = new ClientModel();
		client2.setName("Pedro");
		client2.setLastName("Gomez");
		client2.setDocNumber("1234567891");
		clients.add(client2);

		ClientModel client3 = new ClientModel();
		client3.setName("Maria");
		client3.setLastName("Gonzalez");
		client3.setDocNumber("1234567892");
		clients.add(client3);

		ClientModel client4 = new ClientModel();
		client4.setName("Jose");
		client4.setLastName("Lopez");
		client4.setDocNumber("1234567893");
		clients.add(client4);

		ClientModel client5 = new ClientModel();
		client5.setName("Ana");
		client5.setLastName("Martinez");
		client5.setDocNumber("1234567894");
		clients.add(client5);

		Insert(clientRepository, clients);
	}

	private void CreateProducts() {
		List<ProductModel> products = new ArrayList<>();

		ProductModel product = new ProductModel();
		product.setDescription("Gaseosa cola 1lt");
		product.setCode("123456");
		product.setStock(10);
		product.setPrice(100.00);
		product.setActive(true);
		products.add(product);

		ProductModel product2 = new ProductModel();
		product2.setDescription("Gaseosa lima 1lt");
		product2.setCode("123457");
		product2.setStock(15);
		product2.setPrice(115.00);
		product2.setActive(true);
		products.add(product2);

		ProductModel product3 = new ProductModel();
		product3.setDescription("Gaseosa pomelo 1lt");
		product3.setCode("123458");
		product3.setStock(20);
		product3.setPrice(100.50);
		product3.setActive(true);
		products.add(product3);

		ProductModel product4 = new ProductModel();
		product4.setDescription("Gaseosa cola 1lt");
		product4.setCode("123459");
		product4.setStock(10);
		product4.setPrice(100.00);
		product4.setActive(true);
		products.add(product4);

		ProductModel product5 = new ProductModel();
		product5.setDescription("Papas fritas 1kg");
		product5.setCode("123460");
		product5.setStock(100);
		product5.setPrice(90.00);
		product5.setActive(true);
		products.add(product5);

		ProductModel product6 = new ProductModel();
		product6.setDescription("Palitos salados 500g");
		product6.setCode("123461");
		product6.setStock(50);
		product6.setPrice(30.15);
		product6.setActive(true);
		products.add(product6);

		ProductModel product7 = new ProductModel();
		product7.setDescription("Dulce de leche 500g");
		product7.setCode("123462");
		product7.setStock(11);
		product7.setPrice(80.00);
		product7.setActive(true);
		products.add(product7);

		ProductModel product8 = new ProductModel();
		product8.setDescription("Manteca 500g");
		product8.setCode("123463");
		product8.setStock(15);
		product8.setPrice(60.00);
		product8.setActive(true);
		products.add(product8);

		ProductModel product9 = new ProductModel();
		product9.setDescription("Dulce de membrillo 1k");
		product9.setCode("123464");
		product9.setStock(13);
		product9.setPrice(75.00);
		product9.setActive(true);
		products.add(product9);

		ProductModel product10 = new ProductModel();
		product10.setDescription("Yerba mate 1kg");
		product10.setCode("123465");
		product10.setStock(10);
		product10.setPrice(150.00);
		product10.setActive(true);
		products.add(product10);

		ProductModel product11 = new ProductModel();
		product11.setDescription("Fideos moño 1kg");
		product11.setCode("123466");
		product11.setStock(100);
		product11.setPrice(80.00);
		product11.setActive(true);
		products.add(product11);

		ProductModel product12 = new ProductModel();
		product12.setDescription("ketchul manhells 500gr");
		product12.setCode("123467");
		product12.setStock(50);
		product12.setPrice(75.00);
		product12.setActive(true);
		products.add(product12);

		ProductModel product13 = new ProductModel();
		product13.setDescription("asado de unicornio 1kg");
		product13.setCode("123468");
		product13.setStock(100);
		product13.setPrice(1025.00);
		product13.setActive(true);
		products.add(product13);


		Insert(productRepository, products);
	}

	private void CreateInvoices() {

		List<InvoiceModel> invoices = new ArrayList<>();
		LocalDate date = LocalDate.now();
		SaveInvoice(1L,1, date);
		SaveInvoice(1L,2, date);
		SaveInvoice(2L,3, date);
		SaveInvoice(2L,3, date);
		SaveInvoice(2L,3, date);
		SaveInvoice(3L,4, date);

		/*InvoiceModel invoice = new InvoiceModel();
		Optional<ClientModel> client = clientRepository.findById(1L);
		if(client.isEmpty()) {
			invoice.setClientId(client.get());
			throw new RuntimeException("Client not found");
		}
		invoice.setClientId(client.get());
		invoice.setCreatedAt(date);

		List<InvoiceDetailsModel> invoice1Details = new ArrayList<>();
		for (int i=1; i<4; i++) {
			InvoiceDetailsModel invoiceDetails = new InvoiceDetailsModel();
			invoiceDetails.setInvoice(invoice);
			invoiceDetails.setAmoun(i);
			var product = productRepository.findById(Long.valueOf(i));
			if(product.isEmpty()) {
				throw new RuntimeException("Product not found");
			}
			invoiceDetails.setProductId(product.get());
			invoice1Details.add(invoiceDetails);
		}
		double total = 0;
		for (InvoiceDetailsModel object : invoice1Details) {
			object.setUnitPrice(object.getProductId().getPrice());
			total =+ object.getAmoun()*object.getProductId().getPrice();

		}
		invoice.setTotal(total);
		var savedInvoice = invoiceRepository.save(invoice);
		for (InvoiceDetailsModel object : invoice1Details) {
			object.setInvoice(savedInvoice);
			invoiceDetailsRepository.save(object);
		}

		InvoiceModel invoice2 = new InvoiceModel();
		Optional<ClientModel> client2 = clientRepository.findById(2L);
		if(client2.isEmpty()) {
			invoice.setClientId(client2.get());
			throw new RuntimeException("Client not found");
		}
		invoice2.setClientId(client2.get());
		invoice2.setCreatedAt(date.plusDays(2));

		List<InvoiceDetailsModel> invoice2Details = new ArrayList<>();
		for (int i=2; i<5; i++) {
			InvoiceDetailsModel invoiceDetails = new InvoiceDetailsModel();
			invoiceDetails.setInvoice(invoice2);
			invoiceDetails.setAmoun(i);
			var product = productRepository.findById(Long.valueOf(i));
			if(product.isEmpty()) {
				throw new RuntimeException("Product not found");
			}
			invoiceDetails.setProductId(product.get());
			invoice2Details.add(invoiceDetails);
		}
		double total2 = 0;
		for (InvoiceDetailsModel object : invoice2Details) {
			object.setUnitPrice(object.getProductId().getPrice());
			total =+ object.getAmoun()*object.getProductId().getPrice();

		}
		invoice2.setTotal(total);
		var savedInvoice2 = invoiceRepository.save(invoice2);
		for (InvoiceDetailsModel object : invoice2Details) {
			object.setInvoice(savedInvoice2);
			invoiceDetailsRepository.save(object);
		}*/

	}

	private void SaveInvoice(long i,int j, LocalDate date){
		InvoiceModel invoice = new InvoiceModel();
		Optional<ClientModel> client = clientRepository.findById(i);
		if(client.isEmpty()) {
			invoice.setClientId(client.get());
			throw new RuntimeException("Client not found");
		}
		invoice.setClientId(client.get());
		invoice.setCreatedAt(date.plusDays(j));

		List<InvoiceDetailsModel> invoice1Details = new ArrayList<>();
		int k = j+4;
		while (j<k) {
			InvoiceDetailsModel invoiceDetails = new InvoiceDetailsModel();
			invoiceDetails.setInvoice(invoice);
			invoiceDetails.setAmoun(j);
			var product = productRepository.findById(Long.valueOf(j));
			if(product.isEmpty()) {
				throw new RuntimeException("Product not found");
			}
			invoiceDetails.setProductId(product.get());
			invoice1Details.add(invoiceDetails);
			j++;
		}
		double total = 0;
		for (InvoiceDetailsModel object : invoice1Details) {
			object.setUnitPrice(object.getProductId().getPrice());
			total =+ object.getAmoun()*object.getProductId().getPrice();

		}
		invoice.setTotal(total);
		var savedInvoice = invoiceRepository.save(invoice);
		for (InvoiceDetailsModel object : invoice1Details) {
			object.setInvoice(savedInvoice);
			invoiceDetailsRepository.save(object);
		}
	}

	private <T> void Insert(JpaRepository repository, List<T> objects) {
		for (Object object : objects) {
			repository.save(object);
		}
	}
}
