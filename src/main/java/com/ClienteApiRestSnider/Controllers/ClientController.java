package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {
	@Autowired
	private ClientService service;

	@PostMapping(path = "/")
	public ResponseEntity<Object> create(@RequestBody ClientModel model) throws Exception {
		var createdEntity = this.service.create(model);
		if (createdEntity == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(createdEntity, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity update(ClientModel model, Long id) throws Throwable, Exception {
		var updatedEntity = this.service.update(model, id);
		if (updatedEntity == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.update(updatedEntity, id), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		var deletedEntity = this.service.delete(id);
		if (deletedEntity == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	}

	@GetMapping(path = "/id/{id}")
	public ResponseEntity findById(@PathVariable Long id) throws Throwable, Exception {
		var foundEntity = this.service.findById(id);
		if (foundEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foundEntity, HttpStatus.OK);
	}

	@GetMapping(path = "/")
	public ResponseEntity findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/docnumber/{docNumber}")
	public ResponseEntity getByDocNumber(@PathVariable String docNumber) throws Exception {
		var foundEntity = this.service.findByDocNumber(docNumber);
		if (foundEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foundEntity, HttpStatus.OK);
	}

}
