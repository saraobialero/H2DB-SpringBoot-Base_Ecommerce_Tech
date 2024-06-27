package com.ecommerce.service.interfaces;

import com.ecommerce.exception.ClientGenericsException;
import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.exception.InvalidClientCodeException;
import com.ecommerce.exception.InvalidPasswordException;
import com.ecommerce.model.Client;

import java.util.Optional;

public interface ClientFunctions {
    boolean login(String idClient, String password) throws InvalidClientCodeException, ClientNotFoundException, InvalidPasswordException, ClientGenericsException;
    Optional<Client> getClient(String idClient) throws ClientNotFoundException;
    Optional<Client> getClientByEmail(String email) throws ClientNotFoundException;
}
