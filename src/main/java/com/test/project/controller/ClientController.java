package com.test.project.controller;

import com.test.project.model.Client;
import com.test.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.create(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients() {
        List<Client> list = clientService.getAll();

        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable(value = "id") Long id) {
        Optional<Client> client = clientService.get(id);

        if (client.isPresent())
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(value = "id") Long id, @RequestBody Client update) {
        Optional<Client> client = clientService.update(id, update);

        if (client.isPresent())
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        if (clientService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}