package com.learn.notesapp.controller;

import com.learn.notesapp.commons.Constants;
import com.learn.notesapp.dto.UserDetails;
import com.learn.notesapp.model.User;
import com.learn.notesapp.service.UserService;
import com.learn.notesapp.utility.GenericResponse;
import com.learn.notesapp.utility.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> registerUser(@RequestBody UserDetails userDetails) {
        long startTime = System.currentTimeMillis();
        try {
            User userResult = userService.registerUser(userDetails);
            return ResponseEntity.ok(new GenericResponse(
                    Constants.OK,
                    Constants.USER_FOUND,
                    null,
                    userResult,
                    System.currentTimeMillis() - startTime
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(
                    Constants.USER_NOT_FOUND,
                    e.getMessage(),
                    null,
                    null,
                    System.currentTimeMillis() - startTime
            ));
        }
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDetails request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        User user = userService.login(request.getUserName());

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUserName(), user.getRole());

        // Return token in response
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }


}
