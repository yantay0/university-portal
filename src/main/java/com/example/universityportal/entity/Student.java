package com.example.universityportal.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.universityportal.entity.Department;

import java.util.UUID;

@Entity
@Table(name = "student")
@Getter
@Setter
//@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "year_of_study")
    private int yearOfStudy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
