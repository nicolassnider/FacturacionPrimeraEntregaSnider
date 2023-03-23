package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Exceptions.ClientAlreadyExistsException;
import com.ClienteApiRestSnider.Entities.Client;
import com.ClienteApiRestSnider.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
