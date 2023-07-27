package com.example.universityportal.controller;

import com.example.universityportal.auth.AuthenticationResponse;
import com.example.universityportal.auth.AuthenticationService;
import com.example.universityportal.auth.RegisterRequest;
import com.example.universityportal.entity.User;
import com.example.universityportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/admin")
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final AuthenticationService authService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from Admin controller test");
    }

    @PostMapping("/users")
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public ResponseEntity<AuthenticationResponse> registerNewUser(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }
}
