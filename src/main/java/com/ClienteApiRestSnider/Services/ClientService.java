package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Exceptions.EntityAlreadyExistsException;
import com.ClienteApiRestSnider.Exceptions.EntityNotFoundException;
import com.ClienteApiRestSnider.Repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService implements  IService<ClientModel>{
	@Autowired
	private ClientRepository repository;

	@Override
	public ClientModel create(ClientModel model) throws EntityAlreadyExistsException {
		Optional<ClientModel> modelOp = this.repository.findByDocNumber(model.getDocNumber());
		entityIsPresent(modelOp);
		return this.repository.save(model);
	}

	public ClientModel update(ClientModel model, Long id) throws Exception {
		invalidId(id);
		Optional<ClientModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		ClientModel entityDB = entityOp.get();
		entityDB.setName(model.getName());
		entityDB.setLastName(model.getLastName());
		entityDB.setDocNumber(model.getDocNumber());
		log.info("Entidad actualizada : " + entityDB);
		return this.repository.save(entityDB);
	}

	public ClientModel delete(Long id) throws Exception {
		invalidId(id);
		Optional<ClientModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		ClientModel entityDB = entityOp.get();
		entityDB.setActive(false);
		log.info("Entidad desactivada : " + entityDB);
		return this.repository.save(entityDB);
	}

	public ClientModel findById(Long id) throws EntityNotFoundException, Exception {
		Optional<ClientModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		log.info("La entidad fue encontrada");
		return entityOp.get();
	}

	public ClientModel findByDocNumber(String docNumber) throws EntityNotFoundException {
		Optional<ClientModel> entityOp = this.repository.findByDocNumber(docNumber);
		entityIsEmpty(entityOp);
		log.info("La entidad fue encontrada");
		return entityOp.get();
	}

	public List<ClientModel> findAll() {
		return repository.findAll();
	}

	private void invalidId(Long id) throws Exception {
		log.info("ID INGRESANDO : " + id);
		if (id <= 0) {
			throw new Exception("El id brindado no es valido");
		}
	}

	public void entityIsEmpty(Optional<ClientModel> entityOp) throws EntityNotFoundException {
		if (entityOp.isEmpty()) {
			log.info("La entidad que intenta buscar no existe en la base de datos : ");
			throw new EntityNotFoundException("La entidad que intenta modificar no existe en la base de datos");
		}
		log.info("La entidad fue encontrada");
	}

	public void entityIsPresent(Optional<ClientModel> entityOp) throws EntityAlreadyExistsException {
		if (entityOp.isPresent()) {
			log.info("La entidad que intenta buscar ya existe en la base de datos : ");
			throw new EntityAlreadyExistsException("La entidad que intenta agregar ya existe en la base de datos");
		}
		log.info("La entidad no se encontró");
	}

}
