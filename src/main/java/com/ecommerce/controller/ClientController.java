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

    @GetMapping("client/{idClient}")
    public ResponseEntity<ClientDTO> viewClient(@RequestHeader("Authorization") String token, @PathVariable("idClient") String idClient) throws ClientNotFoundException {


            Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
            //String clientId = claims.getSubject();

            Optional<Client> client = clientFunctions.getClient(idClient);
            ClientDTO clientDTO = convertToDTO(client, ClientDTO.class);


            return client.isEmpty()
                    ? ResponseEntity.notFound().build()
                    : ResponseEntity.ok(clientDTO);

    }

    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
