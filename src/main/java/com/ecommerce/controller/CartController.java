package com.ecommerce.controller;

import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.model.Client;
import com.ecommerce.service.interfaces.ClientFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/ecommerce/api/v1")
public class CartController {
    @Autowired
    private ClientFunctions clientFunctions;

    @GetMapping("client/{idClient}")
    public ResponseEntity<Client> viewClient(@PathVariable("idClient") String idClient) throws ClientNotFoundException {
        Optional<Client> optClient = clientFunctions.getClient(idClient);

        return optClient.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(optClient.get(), HttpStatus.NOT_FOUND);
    }
}
