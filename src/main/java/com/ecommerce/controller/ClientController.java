package com.ecommerce.controller;

import com.ecommerce.dto.ClientDTO;
import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.model.Client;
import com.ecommerce.service.interfaces.ClientFunctions;
import com.ecommerce.utilities.JwtUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ecommerce/api/v1")
public class ClientController {

    @Autowired
    private ClientFunctions clientFunctions;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;

    @GetMapping("client/{email}")
    public ResponseEntity<ClientDTO> viewClient(@RequestHeader("Authorization") String token, @PathVariable("email") String email) throws ClientNotFoundException {


            Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

            Optional<Client> client = clientFunctions.getClientByEmail(email);
            ClientDTO clientDTO = convertToDTO(client, ClientDTO.class);


            return client.isEmpty()
                    ? ResponseEntity.notFound().build()
                    : ResponseEntity.ok(clientDTO);

    }

    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
