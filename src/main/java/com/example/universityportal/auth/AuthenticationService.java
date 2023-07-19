package com.example.universityportal.auth;

import com.example.universityportal.entity.Role;
import com.example.universityportal.entity.User;
import com.example.universityportal.repository.UserRepository;
import com.example.universityportal.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return generateAuthenticationResponseWithJwt(user);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // need to catch exception
        return generateAuthenticationResponseWithJwt(user);
    }

    public AuthenticationResponse generateAuthenticationResponseWithJwt(UserDetails userDetails) {
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
