package com.example.universityportal.service.impl;

import com.example.universityportal.entity.Course;
import com.example.universityportal.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CourseService {
    private final CourseRepository courseRepository;

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + "does not exist"));
    }

    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Course with code " + code + "not found"));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
