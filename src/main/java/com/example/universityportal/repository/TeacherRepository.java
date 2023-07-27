package com.example.universityportal.repository;

import com.example.universityportal.entity.Teacher;
import com.example.universityportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
