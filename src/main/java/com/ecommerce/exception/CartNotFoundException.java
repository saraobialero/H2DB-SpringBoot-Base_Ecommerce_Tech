package com.ecommerce.exception;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(String idCart) {
        super("Cart with  " + idCart + " not found");
    }
}
