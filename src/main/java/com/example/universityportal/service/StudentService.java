package com.example.universityportal.service;

import com.example.universityportal.entity.Student;
import com.example.universityportal.entity.User;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student addStudent(Student student);

    Student updateStudent(Long id, Student student);

    void deleteStudentById(Long id);

    List<Student> getAllStudents();

    Student getStudentById(Long id);
}
