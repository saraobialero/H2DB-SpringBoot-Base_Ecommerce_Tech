package com.ecommerce.exception;

public class NoCartsForClientException extends Exception {
    public NoCartsForClientException(String idClient) {
        super("Client with ID: " + idClient + " hasn't any carts");
    }
}
