package com.assignment.demo.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    private static final String NOT_ALLOWED = "NOT_ALLOWED";

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        LOGGER.info("Attempt to enter. AUTH " + authentication);
        LOGGER.info("NAME " + authentication != null ? authentication.getName() : "auth is null");
        if (authentication.getCredentials() != null && authentication.getCredentials().toString().equals("admin")) {
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                    authentication.getCredentials().toString(), new ArrayList<>());
        } else {
            AuthenticationException ex = new AuthenticationException(NOT_ALLOWED) {
                private static final long serialVersionUID = 8154361891877582369L;
            };
            throw ex;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}