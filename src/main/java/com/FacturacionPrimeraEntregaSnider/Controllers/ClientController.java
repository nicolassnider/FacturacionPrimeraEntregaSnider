package com.FacturacionPrimeraEntregaSnider.Controllers;


import com.FacturacionPrimeraEntregaSnider.Entities.Client;
import com.FacturacionPrimeraEntregaSnider.Entities.Product;
import com.FacturacionPrimeraEntregaSnider.Exceptions.ClientAlreadyExistsException;
import com.FacturacionPrimeraEntregaSnider.Repositories.ClientRepository;
import com.FacturacionPrimeraEntregaSnider.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(path= "/")
    public ResponseEntity create(@RequestBody Client client) throws ClientAlreadyExistsException {
        return new ResponseEntity<>(this.clientService.create(client), HttpStatus.OK);
    }
}
