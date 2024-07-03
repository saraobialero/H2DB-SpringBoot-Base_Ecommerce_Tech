package com.ecommerce.exception;

public class NoOrderForClientException extends Exception{
    public NoOrderForClientException(String message) {
        super(message);
    }
}
