package com.test.project.service;

import com.test.project.model.Client;
import com.test.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> get(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Optional<Client> update(Long id, Client update) {
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

            return Optional.of(clientRepository.save(client));
        } else
            return updatingClient;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return true;
        } else
            return false;
    }
}
