package com.example.universityportal.service.impl;

import com.example.universityportal.repository.UserRepository;
import com.example.universityportal.entity.User;
import com.example.universityportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


//    @Override
//    public User createUser(User user) {
//        Optional<User> localUser = this.userRepository.findByEmail(user.getEmail());
//        if (localUser.isPresent()) {
//            throw new RuntimeException("User already exist");
//        }
//        userRepository.save(user);
//        return user;
//    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
