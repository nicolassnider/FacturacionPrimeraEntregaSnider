package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.DTO.ClientDTO;
import com.ClienteApiRestSnider.DTO.InvoiceDTO;
import com.ClienteApiRestSnider.DTO.InvoiceDetailsDTO;
import com.ClienteApiRestSnider.DTO.ProductDTO;
import com.ClienteApiRestSnider.Entities.InvoiceModel;

import com.ClienteApiRestSnider.Entities.ProductModel;
import com.ClienteApiRestSnider.Exceptions.EntityAlreadyExistsException;
import com.ClienteApiRestSnider.Exceptions.EntityNotFoundException;
import com.ClienteApiRestSnider.Helpers.TimeResponse;
import com.ClienteApiRestSnider.Repositories.ClientRepository;
import com.ClienteApiRestSnider.Repositories.InvoiceDetailsRepository;
import com.ClienteApiRestSnider.Repositories.InvoiceRepository;
import com.ClienteApiRestSnider.Repositories.ProductRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class InvoiceService{
	/*Variables repositorio*/
	@Autowired
	private InvoiceRepository repository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepository;
	@Autowired
	private InvoiceDetailsService invoiceDetailsService;

	/*métodos crud*/
	public InvoiceDTO create(InvoiceModel model) throws Exception {


		try {
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> vars = new HashMap<>();
			vars.put("time", "now");
			var result = restTemplate.getForObject("http://worldclockapi.com/api/json/utc/{time}", TimeResponse.class, vars);
			LocalDate date = result.getCurrentDateTime().atStartOfDay().toLocalDate();
			model.setCreatedAt(date);

		} catch (Exception e) {
			LocalDate date = LocalDate.now();
			model.setCreatedAt(date);
		}

		var client = clientRepository.findById(model.getClientId().getId());

		model.setClientId(client.get());
		model.getInvoiceDetails().forEach(invoiceDetailsModel -> {
			var product = productRepository.findById(invoiceDetailsModel.getProductId().getId());
			var stock = product.get().getStock();
			var amount = invoiceDetailsModel.getAmoun();
			if (stock < amount) {
				throw new RuntimeException("No hay suficiente stock del producto " + product.get().getDescription());
			}
			invoiceDetailsModel.setUnitPrice(product.get().getPrice());
			invoiceDetailsModel.setProductId(product.get());
		});
		var savedInvoice = repository.save(model);
		savedInvoice.getInvoiceDetails().forEach(invoiceDetailsModel -> {
			invoiceDetailsModel.setInvoice(savedInvoice);
			invoiceDetailsRepository.save(invoiceDetailsModel);
			var product = productRepository.findById(invoiceDetailsModel.getProductId().getId());
			var stock = product.get().getStock();
			var amount = invoiceDetailsModel.getAmoun();
			product.get().setStock(stock - amount);
			productRepository.save(product.get());
		});

		model.getInvoiceDetails().forEach(invoiceDetailsModel -> {
			var product = productRepository.findById(invoiceDetailsModel.getProductId().getId());
			var stock = product.get().getStock();
			var amount = invoiceDetailsModel.getAmoun();
			stock = stock - amount;
			product.get().setStock(stock);
			productRepository.save(product.get());
		});

		return setDTO(savedInvoice);
	}

	/*public InvoiceDTO update(InvoiceModel model, Long id) throws Exception {

		invalidId(id);

		Optional<InvoiceModel> entityOp = this.repository.findById(id);

		entityIsEmpty(entityOp);
		InvoiceModel entityDB = entityOp.get();
		entityDB.setTotal(model.getTotal());
		entityDB.setClientId(model.getClientId());
		entityDB.setCreatedAt(model.getCreatedAt());

		log.info("Entidad actualizada : " + entityDB);
		return setDTO(this.repository.save(entityDB));
	}*/

	/*public InvoiceModel delete(Long id) throws Exception {
		// return not implemented exception
		return null;
	}*/

	public InvoiceDTO findById(Long id) throws EntityNotFoundException, Exception {
		Optional<InvoiceModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		return setDTO(entityOp.get());
	}

	public List<InvoiceDTO> findAll() {
		var findAll = repository.findAll();
		return setListDTO(findAll);
	}

	public List<InvoiceDTO> findAllByClientId(Long clientId) throws Exception{
		if(clientId <= 0) {
			throw new Exception("El id brindado no es valido");
		}
		var findAllByClientId = repository.findAllByClientId_Id(clientId);
		return setListDTO(findAllByClientId);
	}

	private List<InvoiceDTO> setListDTO(List<InvoiceModel> invoiceModels){
		List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
		for (InvoiceModel invoiceModel:invoiceModels) {
			{
				invoiceDTOS.add(setDTO(invoiceModel));
			}
		}
		return invoiceDTOS;
	}

	/*métodos privados*/
	private void invalidId(Long id) throws Exception {
		log.info("ID INGRESANDO : " + id);
		if (id <= 0) {
			throw new Exception("El id brindado no es valido");
		}
	}

	private InvoiceDTO setDTO(InvoiceModel invoiceModel){
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setId(invoiceModel.getId());
		invoiceDTO.setTotal(invoiceModel.getTotal());
		invoiceDTO.setCreatedAt(invoiceModel.getCreatedAt());
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(invoiceModel.getClientId().getId());
		clientDTO.setName(invoiceModel.getClientId().getName());
		clientDTO.setLastName(invoiceModel.getClientId().getLastName());
		clientDTO.setDocNumber(invoiceModel.getClientId().getDocNumber());
		invoiceDTO.setClient(clientDTO);
		invoiceDTO.setInvoiceDetails(invoiceDetailsService.findAllByInvoiceId(invoiceModel.getId()));
		return invoiceDTO;
	}

	private void entityIsEmpty(Optional<InvoiceModel> entityOp) throws EntityNotFoundException {
		if (entityOp.isEmpty()) {
			log.info("La entidad que intenta buscar no existe en la base de datos : ");
			throw new EntityNotFoundException("La entidad que intenta modificar no existe en la base de datos");
		}
		log.info("La entidad fue encontrada");
	}

}
