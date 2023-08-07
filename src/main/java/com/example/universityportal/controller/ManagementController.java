package com.example.universityportal.controller;

import com.example.universityportal.entity.User;
import com.example.universityportal.service.UserService;
import com.example.universityportal.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagementController {
    private final UserService userService;
    private final ManagerService managerService;


    @PostMapping("/users/{id}")
    public ResponseEntity<User> updateUserInfo(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @GetMapping
    public ResponseEntity<String> helloManager() {
        return ResponseEntity.ok("hi manager");
    }

    @PostMapping("/teachers/{teacherId}/assign-course")
    public ResponseEntity<String> assignCourseToTeacher(
            @PathVariable Long teacherId, @RequestParam String courseCode
    ) {
        managerService.assignCoursesForTeacher(teacherId, courseCode);
        return ResponseEntity.ok("\"Course assigned successfully to teacher with ID: \"" + teacherId);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
