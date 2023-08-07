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


    public void assignCoursesForTeacher(Long teacherId, String courseCode) {
        var teacher = teacherService.getTeacherById(teacherId);
        var course = courseService.getCourseByCode(courseCode);
        if (!course.getDepartment().equals(teacher.getDepartment())) {
            throw new DepartmentMismatchException("Teacher and course are not from the same department");
        }
        teacher.getCourses().add(course);
        course.getTeachers().add(teacher);
        teacherRepository.save(teacher);
    }


}
