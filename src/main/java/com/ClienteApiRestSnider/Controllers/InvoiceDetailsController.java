package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Entities.InvoiceDetailsModel;
import com.ClienteApiRestSnider.Services.ClientService;
import com.ClienteApiRestSnider.Services.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/invoicedetails")
public class InvoiceDetailsController {

	@Autowired
	private InvoiceDetailsService service;
	/*
	 * 
	 * @PostMapping(path = "/")
	 * public ResponseEntity create(@RequestBody InvoiceDetailsModel model) throws
	 * Exception {
	 * 
	 * return new ResponseEntity<>(this.service.create(model), HttpStatus.CREATED);
	 * }
	 * 
	 * @PutMapping(path = "/{id}")
	 * public ResponseEntity update(InvoiceDetailsModel model, Long id) throws
	 * Throwable, Exception {
	 * return new ResponseEntity<>(this.service.update(model, id),
	 * HttpStatus.CREATED);
	 * }
	 * 
	 * @DeleteMapping(path = "/{id}")
	 * public ResponseEntity delete(@PathVariable Long id) throws Exception {
	 * return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
	 * }
	 * 
	 * @GetMapping(path = "/{id}")
	 * public ResponseEntity findById(Long id) throws Throwable, Exception {
	 * return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
	 * }
	 */

	@GetMapping(path = "/invoice/{invoiceId}")
	public ResponseEntity<Object> findAllByInvoiceId(@PathVariable Long invoiceId) throws Throwable, Exception {
		return new ResponseEntity<>(this.service.findAllByInvoiceId(invoiceId), HttpStatus.OK);
	}

}
