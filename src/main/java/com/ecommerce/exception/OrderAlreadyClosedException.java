package com.ecommerce.exception;

public class OrderAlreadyClosedException extends Exception{
    public OrderAlreadyClosedException(String message) {
        super(message);
    }
}
