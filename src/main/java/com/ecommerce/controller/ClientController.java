package com.ecommerce.controller;

import com.ecommerce.dto.ClientDTO;
import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.model.Client;
import com.ecommerce.service.interfaces.ClientFunctions;
import org.modelmapper.ModelMapper;
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
public class ClientController {

    @Autowired
    private ClientFunctions clientFunctions;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("client/{idClient}")
    public ResponseEntity<ClientDTO> viewClient(@PathVariable("idClient") String idClient) throws ClientNotFoundException {
        Optional<Client> client = clientFunctions.getClient(idClient);
        ClientDTO clientDTO = convertToDTO(client, ClientDTO.class);


        return client.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
