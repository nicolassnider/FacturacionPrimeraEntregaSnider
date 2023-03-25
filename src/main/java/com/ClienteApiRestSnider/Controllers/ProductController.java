package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.ProductModel;
import com.ClienteApiRestSnider.Exceptions.EntityAlreadyExistsException;
import com.ClienteApiRestSnider.Exceptions.EntityNotFoundException;
import com.ClienteApiRestSnider.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProductController implements IController {
	@Autowired
	private ProductService service;

	@PostMapping(path = "/") // postea un nuevo producto
	public ResponseEntity create(@RequestBody ProductModel product) throws EntityAlreadyExistsException {
		return new ResponseEntity<>(this.service.create(product), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}") // hace update de producto
	public ResponseEntity update(@RequestBody ProductModel product, @PathVariable Long id)
			throws EntityNotFoundException, Exception {
		return new ResponseEntity<>(this.service.update(product, id), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}") // desactiva un producto
	public ResponseEntity delete(@PathVariable Long id) throws EntityNotFoundException, Exception {
		return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	}

	@GetMapping(path = "/{code}") // obtiene producto por su code
	public ResponseEntity getByCode(@PathVariable String code) throws EntityNotFoundException, Exception {
		return new ResponseEntity<>(this.service.findByCode(code), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}") // obtiene cliente por su id
	public ResponseEntity getById(@PathVariable Long id) throws EntityNotFoundException, Exception {
		return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/") // obtiene todos los clientes
	public ResponseEntity getAll() throws Exception {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}
}
