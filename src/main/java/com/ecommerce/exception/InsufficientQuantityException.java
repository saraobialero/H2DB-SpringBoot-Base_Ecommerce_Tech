package com.ecommerce.exception;

public class InsufficientQuantityException extends Exception{
    public InsufficientQuantityException() {
        super("Quantity not available");
    }
}
