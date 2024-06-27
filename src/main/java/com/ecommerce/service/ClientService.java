package com.ecommerce.service;

import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.model.Client;
import com.ecommerce.repository.ClientRepository;
import com.ecommerce.service.interfaces.ClientFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements ClientFunctions {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public boolean login(String idClient, String password) {
        return false;
    }

    @Override
    public Optional<Client> getClient(String idClient) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findById(idClient);
        if(client.isEmpty()) {
            throw new ClientNotFoundException(idClient);
        }
        return client;
    }
}
