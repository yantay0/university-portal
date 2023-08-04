package com.example.universityportal.repository;

import com.example.universityportal.entity.Course;
import com.example.universityportal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCode(String code);

    List<Course> findAllByDepartment(Department department);
}
