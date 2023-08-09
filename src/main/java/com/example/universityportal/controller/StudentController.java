package com.example.universityportal.controller;

import com.example.universityportal.entity.Student;
import com.example.universityportal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/students")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<List<Student>> getUsers() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")

    public ResponseEntity<?> getStudent(@PathVariable Long studentId) {
        Student user = studentService.getStudentById(studentId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<Student> updateUser(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, updatedStudent));
    }
}
