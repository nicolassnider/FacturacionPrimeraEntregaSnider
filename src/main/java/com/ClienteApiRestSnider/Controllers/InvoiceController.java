package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.InvoiceModel;

import com.ClienteApiRestSnider.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/invoice")
public class InvoiceController{
	@Autowired
	private InvoiceService service;

	@PostMapping(path = "/")
	public ResponseEntity create(@RequestBody InvoiceModel model) throws Exception {
		return new ResponseEntity<>(this.service.create(model), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity update(InvoiceModel model, Long id) throws Throwable, Exception {
		return new ResponseEntity<>(this.service.update(model, id), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity findById(@PathVariable Long id) throws Throwable, Exception {
		return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/")
	public ResponseEntity findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

}
