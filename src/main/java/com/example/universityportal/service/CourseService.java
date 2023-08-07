package com.example.universityportal.service;

import com.example.universityportal.entity.Course;
import com.example.universityportal.entity.Department;
import com.example.universityportal.repository.CourseRepository;
import com.example.universityportal.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + "does not exist"));
    }

    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Course with code " + code + "not found"));
    }

    public List<Course> getCoursesByDepartment(Department department) {
        return courseRepository.findAllByDepartment(department);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
