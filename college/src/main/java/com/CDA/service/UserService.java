package com.CDA.service;

import com.CDA.exception.InvalidCredentialsException;
import com.CDA.exception.UserNotFoundException;
import com.CDA.exception.UsernameAlreadyExistsException;
import com.CDA.model.User;
import com.CDA.repository.FacultyProfileRepository;
import com.CDA.repository.StudentProfileRepository;
import com.CDA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private FacultyProfileRepository facultyProfileRepository;
    
    @Autowired
    private StudentProfileRepository studentProfileRepository; 

    // Constructor injection for all required beans
    @Autowired
    public UserService(JWTService jwtService, AuthenticationManager authManager,
                       UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register user
    public User register(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' is already taken.");
        }
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Verify user and generate JWT token
    public String verify(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }
        } catch (BadCredentialsException e) {
            return "Invalid username or password";
        }

        return "Authentication failed";
    }


    // Login functionality
    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with username '" + username + "' not found.");
        }
        // Validate password
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new InvalidCredentialsException("Invalid password for username '" + username + "'.");
        }
        return user;
    }

    //    public Object fetchProfileByRole(String username) {
//        User user = userRepository.findByUsername(username)
//                .UserNotFoundException("User not Found");
//
//        if (user.getRole() == User.Role.STUDENT) {
//            return studentProfileRepository.findByUser_Username(user.getUsername());
//        } else if (user.getRole() == User.Role.FACULTY) {
//            return userRepository.findByUsername(user.getUsername());
//        }
//        throw new IllegalArgumentException("Invalid role");
//    }
    // Find user by ID
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with ID '" + id + "' not found.")));
    }

    // Update user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID '" + id + "' not found.");
        }
        userRepository.deleteById(id);
    }

    // Find all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Find user by username
    public Optional<Optional<User>> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
