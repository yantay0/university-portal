package com.example.universityportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/student")
@RestController
@PreAuthorize("hasRole('STUDENT')")
@RequiredArgsConstructor
public class StudentController {

    @GetMapping
    @PreAuthorize("hasAuthority('student:read')")
    public String get() {
        return "GET:: student controller";
    }
}
