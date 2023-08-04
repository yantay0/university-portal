package com.example.universityportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
//@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Student> students;

    @OneToMany(mappedBy = "department",
    cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Course> courses;
}
