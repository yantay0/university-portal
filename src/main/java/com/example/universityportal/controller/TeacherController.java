package com.example.universityportal.controller;

import com.example.universityportal.entity.Course;
import com.example.universityportal.service.TeacherService;
import com.example.universityportal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final CourseService courseService;


    @GetMapping
    public String get() {
        return "GET:: teacher controller";
    }

    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<List<Course>> getAllCourses(@PathVariable Long teacherId) {
        var teacher = teacherService.getTeacherById(teacherId);
        var courses = courseService.getCoursesByDepartment(teacher.getDepartment());
        return ResponseEntity.ok(courses);
    }

}
