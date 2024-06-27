package com.ecommerce.exception;

public class ClientGenericsException extends Exception{
    public ClientGenericsException(String message, Exception e) {
        super(message, e);
    }
}
