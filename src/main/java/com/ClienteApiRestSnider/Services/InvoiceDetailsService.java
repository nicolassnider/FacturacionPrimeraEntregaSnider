package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.DTO.ClientDTO;
import com.ClienteApiRestSnider.DTO.InvoiceDetailsDTO;
import com.ClienteApiRestSnider.DTO.ProductDTO;
import com.ClienteApiRestSnider.Entities.InvoiceDetailsModel;
import com.ClienteApiRestSnider.Exceptions.EntityAlreadyExistsException;
import com.ClienteApiRestSnider.Exceptions.EntityNotFoundException;
import com.ClienteApiRestSnider.Repositories.InvoiceDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceDetailsService{
	@Autowired
	private InvoiceDetailsRepository repository;

	public InvoiceDetailsModel create(InvoiceDetailsModel model) throws EntityAlreadyExistsException {
		return this.repository.save(model);
	}

	public InvoiceDetailsModel update(InvoiceDetailsModel model, Long id) throws Exception {

		invalidId(id);

		Optional<InvoiceDetailsModel> entityOp = this.repository.findById(id);

		InvoiceDetailsModel entityDB = entityOp.get();
		entityDB.setInvoice(model.getInvoice());
		entityDB.setAmoun(model.getAmoun());
		entityDB.setProductId(model.getProductId());
		log.info("Entidad actualizada : " + entityDB);
		return this.repository.save(entityDB);
	}

	public InvoiceDetailsModel delete(Long id) throws Exception {
		return new InvoiceDetailsModel();
	}

	public InvoiceDetailsModel findById(Long id) throws EntityNotFoundException, Exception {
		Optional<InvoiceDetailsModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		return entityOp.get();
	}

	public InvoiceDetailsModel findByCode(String code) throws EntityNotFoundException {
		return new InvoiceDetailsModel();
	}

	public List<InvoiceDetailsModel> findAll() {
		return repository.findAll();
	}

	public List<InvoiceDetailsDTO> findAllByInvoiceId(Long invoiceId) {
		var findAllByInvoiceIdD = repository.findAllByInvoiceId(invoiceId);
		List<InvoiceDetailsDTO> invoiceDetailsDTOS = new ArrayList<>();
		for (InvoiceDetailsModel invoiceDetailsModel:findAllByInvoiceIdD
			 ) {
			InvoiceDetailsDTO invoiceDetailsDTO = new InvoiceDetailsDTO();
			invoiceDetailsDTO.setId(invoiceDetailsModel.getId());
			invoiceDetailsDTO.setAmoun(invoiceDetailsModel.getAmoun());
			invoiceDetailsDTO.setUnitPrice(invoiceDetailsModel.getUnitPrice());
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(invoiceDetailsModel.getProductId().getId());
			productDTO.setCode(invoiceDetailsModel.getProductId().getCode());
			productDTO.setDescription(invoiceDetailsModel.getProductId().getDescription());
			invoiceDetailsDTO.setProduct(productDTO);
			invoiceDetailsDTOS.add(invoiceDetailsDTO);
		}
		return invoiceDetailsDTOS;

	}

	private void invalidId(Long id) throws Exception {
		log.info("ID INGRESANDO : " + id);
		if (id <= 0) {
			throw new Exception("El id brindado no es valido");
		}
	}

	private void entityIsEmpty(Optional<InvoiceDetailsModel> entityOp) throws EntityNotFoundException {
		if (entityOp.isEmpty()) {
			log.info("La entidad que intenta buscar no existe en la base de datos : ");
			throw new EntityNotFoundException("La entidad que intenta modificar no existe en la base de datos");
		}
		log.info("La entidad fue encontrada");
	}


}
