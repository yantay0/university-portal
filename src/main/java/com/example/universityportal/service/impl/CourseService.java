package com.example.universityportal.service.impl;

import com.example.universityportal.entity.Course;
import com.example.universityportal.exception.CourseNotFoundException;
import com.example.universityportal.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CourseService {
    private final CourseRepository courseRepository;

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + "does not exist"));
    }

    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new CourseNotFoundException
                        ("Course with code " + code + "not found"));
    }
}
