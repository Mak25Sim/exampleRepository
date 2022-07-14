package com.test.project.service;

import com.test.project.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client create(Client client);
    List<Client> getAll();
    Optional<Client> get(Long id);
    Optional<Client> update(Long id, Client update);
    boolean delete(Long id);
}
