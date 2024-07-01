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
    public boolean login(String email, String password) throws InvalidClientCodeException, ClientNotFoundException, InvalidPasswordException, ClientGenericsException {

        try {
            Client client = getClientByEmail(email).orElseThrow(() -> new ClientNotFoundException("Client not found for email: " + email));

            if (!client.getEmail().equals(email)) {
                throw new InvalidClientCodeException("Invalid email: " + email);
            }

            if (!client.getPassword().equals(password)) {
                throw new InvalidPasswordException("Invalid password for email: " + email);
            }

        } catch (ClientNotFoundException | InvalidPasswordException | InvalidClientCodeException e) {
            throw e;
        } catch (Exception e) {
            throw new ClientGenericsException("Login failed", e);
        }
        return true;
    }

    @Override
    public Optional<Client> getClient(String email) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findByEmail(email);
        if (client.isEmpty()) {
            throw new ClientNotFoundException("Client not found, email:" + email);
        }
        return client;
    }



    @Override
    public Optional<Client> getClientByEmail(String email) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findByEmail(email);
        if (client.isEmpty()) {
            throw new ClientNotFoundException("Client not found with email: " + email);
        }
        return client;
    }
}
