package com.ecommerce.exception;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException(String idClient) {
    super("Client with ID: " + idClient + " not found");
}
}
