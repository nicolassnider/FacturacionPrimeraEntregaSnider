package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.DTO.ClientDTO;
import com.ClienteApiRestSnider.DTO.GenericModelMapper;
import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Exceptions.EntityAlreadyExistsException;
import com.ClienteApiRestSnider.Exceptions.EntityNotFoundException;
import com.ClienteApiRestSnider.Repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService{
	@Autowired
	private ClientRepository repository;

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

	public ClientDTO findById(Long id) throws EntityNotFoundException, Exception {
		Optional<ClientModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		return mapToDTO(entityOp.get());
	}

	public ClientDTO findByDocNumber(String docNumber) throws EntityNotFoundException {
		Optional<ClientModel> entityOp = this.repository.findByDocNumber(docNumber);
		entityIsEmpty(entityOp);
		return mapToDTO(entityOp.get());
	}

	public List<ClientDTO> findAll() {
		var findAll = this.repository.findAll();
		return mapToDTOList(findAll);
	}

	private void invalidId(Long id) throws Exception {
		log.info("ID INGRESANDO : " + id);
		if (id <= 0) {
			throw new Exception("El id brindado no es valido");
		}
	}

	private void entityIsEmpty(Optional<ClientModel> entityOp) throws EntityNotFoundException {
		if (entityOp.isEmpty()) {
			log.info("La entidad que intenta buscar no existe en la base de datos : ");
			throw new EntityNotFoundException("La entidad que intenta modificar no existe en la base de datos");
		}
		log.info("La entidad fue encontrada");
	}

	private void entityIsPresent(Optional<ClientModel> entityOp) throws EntityAlreadyExistsException {
		if (entityOp.isPresent()) {
			log.info("La entidad que intenta buscar ya existe en la base de datos : ");
			throw new EntityAlreadyExistsException("La entidad que intenta agregar ya existe en la base de datos");
		}
		log.info("La entidad no se encontró");
	}

	private ClientDTO mapToDTO(ClientModel entity){
		ClientDTO dto = new ClientDTO();
		dto = GenericModelMapper.singleInstance().mapToClientDTO(entity);
		return dto;
	}

	private ClientModel mapToEntity(ClientDTO dto){
		ClientModel entity = new ClientModel();
		entity = GenericModelMapper.singleInstance().mapToClientModel(dto);
		return entity;
	}

	private List<ClientDTO> mapToDTOList(List<ClientModel> entityList){
		List<ClientDTO> dtoList = new ArrayList<>();
		for(ClientModel entity : entityList){
			var dto = mapToDTO(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

}
