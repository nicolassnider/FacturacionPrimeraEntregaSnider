package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/clients")
public class ClientController {
	@Autowired
	private ClientService service;

	@PostMapping(path = "/")
	public ResponseEntity create(@RequestBody ClientModel model) throws Exception {
		return new ResponseEntity<>(this.service.create(model), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity update(ClientModel model, Long id) throws Throwable, Exception {
		return new ResponseEntity<>(this.service.update(model, id), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity findById(Long id) throws Throwable, Exception {
		return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/")
	public ResponseEntity findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{docNumber}")
	public ResponseEntity getByDocNumber(@PathVariable String docNumber) throws Exception {
		return new ResponseEntity<>(this.service.findByDocNumber(docNumber), HttpStatus.OK);
	}

}
