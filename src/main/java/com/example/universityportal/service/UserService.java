package com.example.universityportal.service;

//import com.example.universityportal.entity.Role;
import com.example.universityportal.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

//    User getFirstUser();

    List<User> getAllUsers();
}
