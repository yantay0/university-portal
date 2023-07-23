package com.example.universityportal.security;

import com.example.universityportal.entity.Token;
import com.example.universityportal.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import static com.example.universityportal.config.JwtAuthenticationFilter.AUTHORIZATION;
import static com.example.universityportal.config.JwtAuthenticationFilter.BEARER;

@Service
@RequiredArgsConstructor

public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication
    ) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            return;
        }
        jwt = authHeader.substring(BEARER.length());
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}