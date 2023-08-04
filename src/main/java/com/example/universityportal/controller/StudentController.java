package com.example.universityportal.controller;

import com.example.universityportal.entity.Course;
import com.example.universityportal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/student")
@RestController
@RequiredArgsConstructor
public class StudentController {

    @GetMapping
    public String get() {
        return "GET:: student controller";
    }
}
