package com.example.universityportal.service;

//import com.example.universityportal.entity.Role;
import com.example.universityportal.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);


}
