package com.ecommerce.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
   // private final Logger logger;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractToken(request);

            if (token != null) {
                Claims claims = jwtUtility.validateToken(token);

                // Creare un'istanza di autenticazione e impostarla nel contesto di sicurezza
                if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }
        } catch (JwtException e) {
            // Log dell'errore JWT
            //logger.error("Invalid JWT token: {}", e.getMessage());
            // Restituisci una risposta 401 (Unauthorized)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (RuntimeException e) {
            // Log dell'errore interno del server
            //logger.error("Internal server error: {}", e.getMessage());
            // Restituisci una risposta 500 (Internal Server Error)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Passa la richiesta al filtro successivo nella catena
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Rimuovi "Bearer " dalla stringa del token
            return bearerToken.substring(7);
        }
        return null;
    }

}
