package com.example.universityportal.service;

import com.example.universityportal.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(Long id);

    List<Student> getAllStudents();

    Student findStudentById(Long id);
}
