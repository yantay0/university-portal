package com.example.universityportal.service.impl;

import com.example.universityportal.entity.Student;
import com.example.universityportal.entity.Student;
import com.example.universityportal.repository.StudentRepository;
import com.example.universityportal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        var student = studentRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("Student with id " + id + " does not exist"));
        student.setDepartment(updatedStudent.getDepartment());
        student.setYearOfStudy(updatedStudent.getYearOfStudy());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Student with id " + id + "not found"));
    }
}
