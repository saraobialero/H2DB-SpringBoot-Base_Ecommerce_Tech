package com.ecommerce.exception;

public class CartAlreadySavedException extends Exception {
    public CartAlreadySavedException(String message) {
        super(message);
    }
}

