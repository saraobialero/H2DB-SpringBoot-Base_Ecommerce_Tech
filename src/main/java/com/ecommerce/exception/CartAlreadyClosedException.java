package com.ecommerce.exception;

public class CartAlreadyClosedException extends Exception {
    public CartAlreadyClosedException(String message) {
        super(message);
    }
}

