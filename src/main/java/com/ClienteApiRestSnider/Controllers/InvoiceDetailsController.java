package com.ClienteApiRestSnider.Controllers;

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

	/*devolverá la lista de items según el id de invoice*/
	@GetMapping(path = "/invoice/{invoiceId}")
	public ResponseEntity<Object> findAllByInvoiceId(@PathVariable Long invoiceId) throws Throwable, Exception {
		return new ResponseEntity<>(this.service.findAllByInvoiceId(invoiceId), HttpStatus.OK);
	}

}
