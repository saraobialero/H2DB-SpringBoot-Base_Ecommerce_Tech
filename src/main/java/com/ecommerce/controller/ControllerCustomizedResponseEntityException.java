package com.ecommerce.controller;

import com.ecommerce.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ControllerCustomizedResponseEntityException extends ResponseEntityExceptionHandler {

    // CLIENT EXCEPTION
    @ExceptionHandler(ClientNotFoundException.class)
    public final ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public final ResponseEntity<Object> handleInvalidPasswordException(InvalidPasswordException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidClientCodeException.class)
    public final ResponseEntity<Object> handleInvalidClientCodeException(InvalidClientCodeException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientGenericsException.class)
    public final ResponseEntity<Object> handleClientGenericsException(ClientGenericsException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }
 /*
    // ORDER EXCEPTION
    @ExceptionHandler(OrderAlreadyConfirmedException.class)
    public final ResponseEntity<Object> handleOrderAlreadyConfirmedException(OrderAlreadyConfirmedException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoOrderForClientException.class)
    public final ResponseEntity<Object> handleNoOrderForClientException(NoOrderForClientException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NO_CONTENT);
    }
    */


    // Build ResponseEntyty to reduce boilerPlate
    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request, HttpStatus status) {
        ExceptionResponse exResp = new ExceptionResponse(new Date(),
                                        ex.getMessage(),
                                        request.getDescription(true));
        return new ResponseEntity<>(exResp, new HttpHeaders(), status);
    }
}
