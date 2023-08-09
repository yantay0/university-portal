package com.example.universityportal.controller;

import com.example.universityportal.auth.AuthenticationResponse;
import com.example.universityportal.auth.AuthenticationService;
import com.example.universityportal.auth.RegisterRequest;
import com.example.universityportal.entity.Course;
import com.example.universityportal.entity.Teacher;
import com.example.universityportal.entity.User;
import com.example.universityportal.service.AssignCourseService;
import com.example.universityportal.service.TeacherService;
import com.example.universityportal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final AssignCourseService assignCourseService;
    private final AuthenticationService authService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers() {
        List<Teacher> teachers= teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<?> getUser(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping("/teachers/{teacherId}/assign-course")
    public ResponseEntity<String> assignCourseToTeacher(
            @PathVariable Long teacherId, @RequestParam String courseCode
    ) {
        assignCourseService.assignCourseForTeacher(teacherId, courseCode);
        return ResponseEntity.ok("\"Course assigned successfully to teacher with ID: \"" + teacherId);
    }





//    @GetMapping
//    public String get() {
//        return "GET:: teacher controller";
//    }
//
//    @GetMapping("/{teacherId}/courses")
//    public ResponseEntity<List<Course>> getAllCourses(@PathVariable Long teacherId) {
//        var teacher = teacherService.getTeacherById(teacherId);
//        var courses = courseService.getCoursesByDepartment(teacher.getDepartment());
//        return ResponseEntity.ok(courses);
//    }



}
