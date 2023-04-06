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

	/*Una factura no debería modificarse*/
	/*@PutMapping(path = "/{id}")
	public ResponseEntity update(InvoiceModel model, Long id) throws Throwable, Exception {
		if(id<=0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.update(model, id), HttpStatus.CREATED);
	}*/

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		if(id<=0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity findById(@PathVariable Long id) throws Throwable, Exception {
		if(id<=0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
	}

	@GetMapping(path="/client/{clientId}")
	public ResponseEntity findAllByClientId(@PathVariable Long clientId) throws Throwable, Exception {
		if(clientId<=0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.service.findAllByClientId(clientId), HttpStatus.OK);
	}

	@GetMapping(path = "/")
	public ResponseEntity findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

}
