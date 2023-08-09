package com.example.universityportal.service.impl;

import com.example.universityportal.repository.UserRepository;
import com.example.universityportal.entity.User;
import com.example.universityportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User by id " + id + " was not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("User with id " + id + " does not exist"));
        user.setEmail(updatedUser.getEmail());
        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        return userRepository.save(user);
    }

}
