package com.ecommerce.service.interfaces;

import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.model.Client;

import java.util.Optional;

public interface ClientFunctions {
    boolean login(String idClient, String password);
    Optional<Client> getClient(String idClient) throws ClientNotFoundException;


}
