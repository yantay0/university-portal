package com.example.universityportal.service;

import com.example.universityportal.exception.DepartmentMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignCourseService {
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final StudentService studentService;

    public void assignCourseForTeacher(Long teacherId, String courseCode) {
        var teacher = teacherService.getTeacherById(teacherId);
        var course = courseService.getCourseByCode(courseCode);
        if (!course.getDepartment().equals(teacher.getDepartment())) {
            throw new DepartmentMismatchException("Teacher and course are not from the same department");
        }
        teacher.getCourses().add(course);
        course.getTeachers().add(teacher);
        teacherService.updateTeacher(teacher);
    }

//    public void assignCourseForStudent(Long studentId, String courseCode) {
//        var student = studentService.getStudentById(studentId);
//        var course = courseService.getCourseByCode(courseCode);
//        if (!course.getDepartment().equals(student.getDepartment())) {
//            throw new DepartmentMismatchException("Student and course are not from the same department");
//        }
//        student.getCourses().add(course);
//        course.getStudents().add(student);
//        studentService.updateStudent(student);
//    }
}
