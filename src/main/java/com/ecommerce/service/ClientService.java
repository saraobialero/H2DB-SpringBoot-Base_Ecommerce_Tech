package com.ecommerce.service;

import com.ecommerce.exception.ClientGenericsException;
import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.exception.InvalidClientCodeException;
import com.ecommerce.exception.InvalidPasswordException;
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
    public boolean login(String idClient, String password) throws InvalidClientCodeException, ClientNotFoundException, InvalidPasswordException, ClientGenericsException {

        try {
            Client client = getClient(idClient).orElseThrow(() -> new ClientNotFoundException(idClient));
            if (!client.getIdClient().equals(idClient)) throw new InvalidClientCodeException(idClient);
            System.out.println(client.getIdClient());
            if (!client.getPassword().equals(password)) throw new InvalidPasswordException(idClient);
            System.out.println(client.getPassword());
        } catch (ClientNotFoundException | InvalidPasswordException | InvalidClientCodeException e) {
            throw e;
        } catch (Exception e) {
            throw new ClientGenericsException("Login failed", e);
        }
        return true;
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
