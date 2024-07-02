package com.ecommerce.controller;

import com.ecommerce.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    //CART EXCEPTION
    @ExceptionHandler(NoCartsForClientException.class)
    public final ResponseEntity<Object> handleNoCartsForClientException(NoCartsForClientException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public final ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CartAlreadyClosedException.class)
    public final ResponseEntity<Object> handleCartAlreadyClosedException(CartAlreadyClosedException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartAlreadySavedException.class)
    public final ResponseEntity<Object> handleCartAlreadySavedException(CartAlreadySavedException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    // ORDER EXCEPTION

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    // ARTICLE EXCEPTION
    @ExceptionHandler(ArticleNotFoundException.class)
    public final ResponseEntity<Object> handleArticleNotFoundException(ArticleNotFoundException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public final ResponseEntity<Object> handleInsufficientQuantityException(InsufficientQuantityException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }


    // BUILD RESPONSE ENTITY TO REDUCE BOILER PLATE
    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request, HttpStatus status) {
        ExceptionResponse exResp = new ExceptionResponse(new Date(),
                                        ex.getMessage(),
                                        request.getDescription(true));
        return new ResponseEntity<>(exResp, new HttpHeaders(), status);
    }
}
