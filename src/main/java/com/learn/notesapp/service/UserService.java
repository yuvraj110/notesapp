package com.learn.notesapp.service;

import com.learn.notesapp.dto.UserDetails;
import com.learn.notesapp.model.User;

public interface UserService {
    User registerUser(UserDetails user);

    User login(String userName);
}
