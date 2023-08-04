package com.example.universityportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter
@Setter
//@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "prerequisite_id")
    @ToString.Exclude
    private Course prerequisite;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @JsonIgnore
    private final Department department;

    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    private List<Teacher> teachers;

}