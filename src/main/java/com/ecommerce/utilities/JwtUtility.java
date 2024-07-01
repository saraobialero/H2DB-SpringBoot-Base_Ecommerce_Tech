package com.ecommerce.utilities;


import com.ecommerce.config.JwtConfig;
import com.ecommerce.model.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {
    @Autowired
    private JwtConfig jwtConfig;

    public String buildToken (Map<String, Object> claims, Client client, Integer expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(client.getEmail())
                .setSubject(client.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignToken(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSignToken() {
        byte[] keyByte = Decoders.BASE64.decode(jwtConfig.getJwtSecretKey());
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String generateAccessToken(Client client) {
        return buildToken(new HashMap<>(), client, jwtConfig.getJwtExpiredAccessToken());

    }

    public String generateRefreshToken(Client client) {
        return buildToken(new HashMap<>(), client, jwtConfig.getJwtExpiredRefreshToken());

    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSignToken())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new JwtException("not valid Token", e);
        }
    }

}

