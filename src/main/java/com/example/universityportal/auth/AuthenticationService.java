package com.example.universityportal.auth;

import com.example.universityportal.entity.*;
import com.example.universityportal.repository.StudentRepository;
import com.example.universityportal.repository.TokenRepository;
import com.example.universityportal.repository.UserRepository;
import com.example.universityportal.security.JwtAuthenticationException;
import com.example.universityportal.security.JwtService;
import com.example.universityportal.service.StudentService;
import com.example.universityportal.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        if (user.getRole() == Role.STUDENT) {
            var student = Student.builder()
                    .user(user).build();
            studentService.addStudent(student);
        }
        else if (user.getRole() == Role.TEACHER) {
            var teacher = Teacher.builder()
                    .user(user).build();
            teacherService.addTeacher(teacher);

        }
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + request.getEmail() + " not found")); // need to catch exception
            var jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (AuthenticationException ex) {
            throw new JwtAuthenticationException("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
