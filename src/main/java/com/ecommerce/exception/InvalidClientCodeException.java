package com.ecommerce.exception;

public class InvalidClientCodeException extends Exception {
    public InvalidClientCodeException(String idClient) {
        super("Invalid Username for Client with ID: " + idClient);
    }
}
