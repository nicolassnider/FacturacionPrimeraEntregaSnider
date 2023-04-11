package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.InvoiceModel;

import com.ClienteApiRestSnider.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService service;

	@PostMapping(path = "/")
	public ResponseEntity<Object> create(@RequestBody InvoiceModel model) throws Exception {
		return new ResponseEntity<>(this.service.create(model), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) throws Throwable, Exception {
		if (id <= 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/client/{clientId}")
	public ResponseEntity<Object> findAllByClientId(@PathVariable Long clientId) throws Throwable, Exception {
		if (clientId <= 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.findAllByClientId(clientId), HttpStatus.OK);
	}

	@GetMapping(path = "/")
	public ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

}
