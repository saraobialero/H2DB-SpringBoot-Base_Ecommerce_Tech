package com.ecommerce.exception;

public class NoOrderDetailForOrderException extends Exception{
    public NoOrderDetailForOrderException(String message) {
        super(message);
    }
}
