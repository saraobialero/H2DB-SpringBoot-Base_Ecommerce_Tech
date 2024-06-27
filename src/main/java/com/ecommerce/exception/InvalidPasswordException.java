package com.ecommerce.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String idClient) {
        super("Invalid Password for Client with ID: " + idClient);
    }
}
