package com.ecommerce.exception;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(int idCart) {
        super("Cart with  " + idCart + " not found");
    }
}
