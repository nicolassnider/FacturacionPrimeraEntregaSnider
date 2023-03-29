package com.ClienteApiRestSnider.Services;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Entities.ProductModel;
import com.ClienteApiRestSnider.Exceptions.EntityAlreadyExistsException;
import com.ClienteApiRestSnider.Exceptions.EntityNotFoundException;
import com.ClienteApiRestSnider.Repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService{
	@Autowired
	private ProductRepository repository;

	public ProductModel create(ProductModel model) throws EntityAlreadyExistsException {
		Optional<ProductModel> modelOp = this.repository.findByCode(model.getCode());
		entityIsPresent(modelOp);
		return this.repository.save(model);
	}

	public ProductModel update(ProductModel model, Long id) throws Exception {

		invalidId(id);

		Optional<ProductModel> entityOp = this.repository.findById(id);

		entityIsEmpty(entityOp);
		ProductModel entityDB = entityOp.get();
		entityDB.setCode(model.getCode());
		entityDB.setPrice(model.getPrice());
		entityDB.setDescription(model.getDescription());
		entityDB.setStock(model.getStock());
		log.info("Entidad actualizada : " + entityDB);
		return this.repository.save(entityDB);
	}

	public ProductModel delete(Long id) throws Exception {
		invalidId(id);
		Optional<ProductModel> entityOp = this.repository.findById(id);

		entityIsEmpty(entityOp);
		ProductModel entityDB = entityOp.get();
		entityDB.setActive(false);
		return this.repository.save(entityDB);
	}

	public ProductModel findById(Long id) throws EntityNotFoundException, Exception {
		Optional<ProductModel> entityOp = this.repository.findById(id);
		entityIsEmpty(entityOp);
		return entityOp.get();
	}

	public ProductModel findByCode(String code) throws EntityNotFoundException {
		Optional<ProductModel> entityOp = this.repository.findByCode(code);
		entityIsEmpty(entityOp);
		return entityOp.get();
	}

	public List<ProductModel> findAll() {
		return repository.findAll();
	}

	private void invalidId(Long id) throws Exception {
		log.info("ID INGRESANDO : " + id);
		if (id <= 0) {
			throw new Exception("El id brindado no es valido");
		}
	}

	private void entityIsEmpty(Optional<ProductModel> entityOp) throws EntityNotFoundException {
		if (entityOp.isEmpty()) {
			log.info("La entidad que intenta buscar no existe en la base de datos : ");
			throw new EntityNotFoundException("La entidad que intenta modificar no existe en la base de datos");
		}
		log.info("La entidad fue encontrada");
	}

	private void entityIsPresent(Optional<ProductModel> entityOp) throws EntityAlreadyExistsException {
		if (entityOp.isPresent()) {
			log.info("La entidad que intenta buscar ya existe en la base de datos : ");
			throw new EntityAlreadyExistsException("La entidad que intenta agregar ya existe en la base de datos");
		}
		log.info("La entidad no se encontró");
	}

}
