package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*Indica que esta clase es un crontroller*/
@RestController
@CrossOrigin(origins = "*")
/* Ruta de controller */
@RequestMapping(path = "api/client")
public class ClientController {

	/* Instancia de servicio */
	@Autowired
	private ClientService service;

	/* Endpoint de creación de entidad */
	@PostMapping(path = "/")
	public ResponseEntity<Object> create(@RequestBody ClientModel model) throws Exception {
		var createdEntity = this.service.create(model);
		return new ResponseEntity<Object>(createdEntity, HttpStatus.CREATED);
	}

	/* Endpoint para obtener una entidad por su id */
	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> update(@RequestBody ClientModel model, @PathVariable Long id)
			throws Throwable, Exception {
		if (id == null || id <= 0) {
			return new ResponseEntity<>(new Object() {
				public String message = "invalid or null id";
			}, HttpStatus.BAD_REQUEST);
		}

		var updatedEntity = this.service.update(model, id);
		if (updatedEntity == null) {
			return new ResponseEntity<>(new Object() {
				public String message = "invalid or null entity";
			}, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.service.update(updatedEntity, id), HttpStatus.CREATED);
	}

	/* Endpoint para desactivar una entidad por su id */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception {
		if (id == null || id <= 0) {
			return new ResponseEntity<>(new Object() {
				public String message = "invalid or null id";
			}, HttpStatus.BAD_REQUEST);
		}

		var deletedEntity = this.service.delete(id);
		if (deletedEntity == null) {
			return new ResponseEntity<>(new Object() {
				public String message = "invalid or null entity";
			}, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	}

	/* Endpoint para obtener una entidad por su id */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) throws Throwable, Exception {
		if (id == null || id <= 0) {
			return new ResponseEntity<>(new Object() {
				public String message = "invalid or null id";
			}, HttpStatus.BAD_REQUEST);
		}

		var foundEntity = this.service.findById(id);
		if (foundEntity == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foundEntity, HttpStatus.OK);
	}

	/* Endpoint para obtener todas las entidades */
	@GetMapping(path = "/")
	public ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

	/* Endpoint para obtener una entidad por su número de documento */
	@GetMapping(path = "/docnumber/{docNumber}")
	public ResponseEntity<Object> getByDocNumber(@PathVariable String docNumber) throws Exception {
		if (docNumber == null || docNumber.length() <= 8 || docNumber.length() >= 15) {
			return new ResponseEntity<>(new Object() {
				public String message = "invalid or null docNumber";
			}, HttpStatus.BAD_REQUEST);
		}
		var foundEntity = this.service.findByDocNumber(docNumber);
		if (foundEntity == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foundEntity, HttpStatus.OK);
	}

}
