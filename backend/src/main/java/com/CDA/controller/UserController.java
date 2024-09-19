package com.CDA.controller;

import com.CDA.model.LoginResponse;
import com.CDA.model.User;
import com.CDA.service.MyUserDetailsService;
import com.CDA.service.UserService;
import com.CDA.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService, JWTService jwtService, MyUserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.register(user);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userService.login(loginUser.getUsername(), loginUser.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }

        User.Role role = user.getRole();
        if (role == null) {
            return new ResponseEntity<>("User role is not set", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return switch (role) {
            case STUDENT ->
                // Redirect or handle student role
                    ResponseEntity.ok().body(new LoginResponse(jwtService.generateToken(user.getUsername()), "STUDENT"));
            case FACULTY ->
                // Redirect or handle faculty role
                    ResponseEntity.ok().body(new LoginResponse(jwtService.generateToken(user.getUsername()), "FACULTY"));
            case ADMIN ->
                // Redirect or handle admin role
                    ResponseEntity.ok().body(new LoginResponse(jwtService.generateToken(user.getUsername()), "ADMIN"));
            default -> new ResponseEntity<>("Unexpected role", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }


    // Get user details based on token
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String token) {
        // Extract the JWT token from the "Bearer token" format
        String jwtToken = token.substring(7);

        // Extract the username from the token
        String username = jwtService.extractUserName(jwtToken);

        // Load the user details from the username

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Validate the token and user details
        if (!jwtService.validateToken(jwtToken, userDetails)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Find the user profile by username if the token is valid
        Optional<User> user = userService.findByUsername(username);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
