package com.test.project.controller;

import com.test.project.model.Client;
import com.test.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients() {
        List<Client> list = clientRepository.findAll();

        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable(value = "id") Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent())
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(value = "id") Long id, @RequestBody Client update) {
        Optional<Client> updatingClient = clientRepository.findById(id);

        if (updatingClient.isPresent()) {
            Client client = updatingClient.get();
            if (update.getName() != null)
                client.setName(update.getName());
            if (update.getBirthdate() != null)
                client.setBirthdate(update.getBirthdate());
            if (update.getEmail() != null)
                client.setEmail(update.getEmail());
            if (update.getPhone() != null)
                client.setPhone(update.getPhone());
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}