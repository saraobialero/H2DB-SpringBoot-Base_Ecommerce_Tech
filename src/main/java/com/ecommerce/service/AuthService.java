package com.ecommerce.service;

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
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        boolean isAuthenticated = clientFunctions.login(email, password);

        if (isAuthenticated) {
            Optional<Client> optClient = clientFunctions.getClientByEmail(email);
            if (optClient.isPresent()) {
                String token = jwtUtility.generateAccessToken(optClient.get());
                return new AuthResponse(token);
            }
                throw new ClientNotFoundException(email);
        }
        //CONDITION NOT AUTHENTICATED
        throw new InvalidPasswordException(email);
    }
}
