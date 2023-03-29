package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.Entities.InvoiceModel;

import com.ClienteApiRestSnider.Entities.ProductModel;
import com.ClienteApiRestSnider.Exceptions.EntityAlreadyExistsException;
import com.ClienteApiRestSnider.Exceptions.EntityNotFoundException;
import com.ClienteApiRestSnider.Repositories.ClientRepository;
import com.ClienteApiRestSnider.Repositories.InvoiceRepository;
import com.ClienteApiRestSnider.Repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceService{
	@Autowired
	private InvoiceRepository repository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;

	public InvoiceModel create(InvoiceModel model) throws Exception {
		LocalDate date = LocalDate.now();
		model.setCreatedAt(date);
		var client = clientRepository.findById(model.getClientId().getId());

		model.setClientId(client.get());
		model.setCreatedAt(date);
		return this.repository.save(model);
	}

	public InvoiceModel update(InvoiceModel model, Long id) throws Exception {

		invalidId(id);

		Optional<InvoiceModel> entityOp = this.repository.findById(id);

		entityIsEmpty(entityOp);
		InvoiceModel entityDB = entityOp.get();
		entityDB.setTotal(model.getTotal());
		entityDB.setClientId(model.getClientId());
		entityDB.setCreatedAt(model.getCreatedAt());

		log.info("Entidad actualizada : " + entityDB);
		return this.repository.save(entityDB);
	}

	public InvoiceModel delete(Long id) throws Exception {
		// return not implemented exception
		return null;
	}

	public InvoiceModel findById(Long id) throws EntityNotFoundException, Exception {
		Optional<InvoiceModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		return entityOp.get();
	}

	public List<InvoiceModel> findAll() {
		return repository.findAll();
	}

	private void invalidId(Long id) throws Exception {
		log.info("ID INGRESANDO : " + id);
		if (id <= 0) {
			throw new Exception("El id brindado no es valido");
		}
	}

	private void entityIsEmpty(Optional<InvoiceModel> entityOp) throws EntityNotFoundException {
		if (entityOp.isEmpty()) {
			log.info("La entidad que intenta buscar no existe en la base de datos : ");
			throw new EntityNotFoundException("La entidad que intenta modificar no existe en la base de datos");
		}
		log.info("La entidad fue encontrada");
	}

	private void entityIsPresent(Optional<InvoiceModel> entityOp) throws EntityAlreadyExistsException {
		if (entityOp.isPresent()) {
			log.info("La entidad que intenta buscar ya existe en la base de datos : ");
			throw new EntityAlreadyExistsException("La entidad que intenta agregar ya existe en la base de datos");
		}
		log.info("La entidad no se encontró");
	}

}
