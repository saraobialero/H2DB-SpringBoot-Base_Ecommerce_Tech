package com.ecommerce.service;

import com.ecommerce.config.JwtConfig;
import com.ecommerce.exception.ClientGenericsException;
import com.ecommerce.exception.ClientNotFoundException;
import com.ecommerce.exception.InvalidClientCodeException;
import com.ecommerce.exception.InvalidPasswordException;
import com.ecommerce.model.AuthRequest;
import com.ecommerce.model.AuthResponse;
import com.ecommerce.model.Client;
import com.ecommerce.service.interfaces.ClientFunctions;
import com.ecommerce.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private ClientFunctions clientFunctions;

    public AuthResponse authenticate(AuthRequest authRequest) throws InvalidClientCodeException, ClientNotFoundException, InvalidPasswordException, ClientGenericsException {
        String idClient = authRequest.getIdClient();
        String password = authRequest.getPassword();
        System.out.println(password);
        System.out.println("Authenticating client: " + idClient);

        boolean isAuthenticated = clientFunctions.login(idClient, password);
        System.out.println("Is authenticated: " + isAuthenticated);

        if (isAuthenticated) {
            Optional<Client> optClient = clientFunctions.getClient(idClient);
            if (optClient.isPresent()) {
                System.out.println("Client found for token generation: " + idClient);
                String token = jwtUtility.generateAccessToken(optClient.get());
                return new AuthResponse(token);
            } else {
                System.out.println("Client not found after authentication: " + idClient);
                throw new ClientNotFoundException(idClient);
            }
        }

        throw new InvalidPasswordException(idClient);
    }
}
