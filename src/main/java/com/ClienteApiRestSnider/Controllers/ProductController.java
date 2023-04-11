package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.ProductModel;
import com.ClienteApiRestSnider.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping(path = "/")
	public ResponseEntity<Object> create(@RequestBody ProductModel model) throws Exception {
		var createdEntity = this.service.create(model);
		if (createdEntity == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(createdEntity, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> update(@RequestBody ProductModel model, @PathVariable Long id)
			throws Throwable, Exception {
		if (id <= 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		var updatedEntity = this.service.update(model, id);
		if (updatedEntity == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.update(updatedEntity, id), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception {
		if (id <= 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		var deletedEntity = this.service.delete(id);
		if (deletedEntity == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	}

	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) throws Throwable, Exception {
		if (id <= 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		var foundEntity = this.service.findById(id);
		if (foundEntity == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foundEntity, HttpStatus.OK);
	}

	@GetMapping(path = "/")
	public ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/bestseller")
	public ResponseEntity<Object> listBestSeller() {
		return new ResponseEntity<>(this.service.listBestSeller(), HttpStatus.OK);
	}

	@GetMapping(path = "/code/{code}")
	public ResponseEntity<Object> getByDocNumber(@PathVariable String code) throws Exception {
		if (code.isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (code.length() != 6)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		var foundEntity = this.service.findByCode(code);
		if (foundEntity == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foundEntity, HttpStatus.OK);
	}

}
