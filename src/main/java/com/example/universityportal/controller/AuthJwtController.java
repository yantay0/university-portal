package com.example.universityportal.controller;

import com.example.universityportal.auth.AuthenticationRequest;
import com.example.universityportal.auth.AuthenticationResponse;
import com.example.universityportal.auth.AuthenticationService;
import com.example.universityportal.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthJwtController {

    private final AuthenticationService authService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('admin:create', 'manager:create')")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

}
