package com.learn.notesapp.service.impl;

import com.learn.notesapp.dto.UserDetails;
import com.learn.notesapp.model.User;
import com.learn.notesapp.repository.UserRepository;
import com.learn.notesapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDetails userDetails) {
        if(repository.findByUserName(userDetails.getUserName()).isPresent()){
            return new User();
        }
        User user =  new User();
        user.setUserName(userDetails.getUserName());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setRole(userDetails.getRole());
        return repository.save(user);
    }

    @Override
    public User login(String userName) {
        Optional<User> user =  repository.findByUserName(userName);
        return user.orElse(null);
    }
}
