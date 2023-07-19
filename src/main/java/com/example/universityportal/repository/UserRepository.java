package com.example.universityportal.repository;

import com.example.universityportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("UPDATE User u SET u.firstname = :firstName WHERE u.email = :email")
    @Modifying
    void updateUserFirstName(String email, String firstName);

}
