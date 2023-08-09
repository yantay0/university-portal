package com.example.universityportal.controller;

import com.example.universityportal.entity.Student;
import com.example.universityportal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/students")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STUDENT')")
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable Long studentId) {
        Student user = studentService.getStudentById(studentId);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/{studentId}")
    public ResponseEntity<Student> updateUser(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, updatedStudent));
    }
}
