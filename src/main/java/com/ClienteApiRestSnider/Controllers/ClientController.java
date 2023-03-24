package com.ClienteApiRestSnider.Controllers;

import com.ClienteApiRestSnider.Exceptions.ClientAlreadyExistsException;
import com.ClienteApiRestSnider.Entities.ClientModel;
import com.ClienteApiRestSnider.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(path= "/") //postea un nuevo cliente
    public ResponseEntity create(@RequestBody ClientModel client) throws ClientAlreadyExistsException {
        return new ResponseEntity<>(this.clientService.create(client), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}") //hace update de cliente
    public ResponseEntity update(@RequestBody ClientModel client, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.clientService.update(client,id), HttpStatus.CREATED );
    }
    @DeleteMapping(path = "/{id}") //desactiva un cliente
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.clientService.deleteClient(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{docNumber}") //obtiene cliente por su numero de documento
    public ResponseEntity getByDocNumber(@PathVariable String docNumber) throws Exception {
        return new ResponseEntity<>(this.clientService.findByDocNumber(docNumber), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}") //obtiene cliente por su id
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.clientService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/") //obtiene todos los clientes
    public ResponseEntity getAll() throws Exception {
        return new ResponseEntity<>(this.clientService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/edad")
    public ResponseEntity getAge(@PathVariable Long id) throws Exception {
        var edad = this.clientService.getAnios(id);
        HashMap<String,Object> rsp = new HashMap<>();
        rsp.put("edad",edad);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }




}
