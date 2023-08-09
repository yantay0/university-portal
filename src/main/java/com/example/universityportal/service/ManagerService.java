package com.example.universityportal.service;

import com.example.universityportal.exception.DepartmentMismatchException;
import com.example.universityportal.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final TeacherRepository teacherRepository;





}
