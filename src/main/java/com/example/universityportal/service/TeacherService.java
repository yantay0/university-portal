package com.example.universityportal.service;

import com.example.universityportal.entity.Teacher;
import com.example.universityportal.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    Teacher addTeacher(Teacher teacher);

    Teacher updateTeacher(Teacher teacher);

    Teacher getTeacherById(Long id);

    void deleteTeacher(Long id);
}
