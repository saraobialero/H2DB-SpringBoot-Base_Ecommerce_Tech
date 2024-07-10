package com.ecommerce.exception;

public class NoArticlesException extends RuntimeException{
    public NoArticlesException(String message) {
        super(message);
    }
}
