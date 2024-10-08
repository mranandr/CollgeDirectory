package com.CDA.service;

import com.CDA.exception.InvalidCredentialsException;
import com.CDA.exception.UserNotFoundException;
import com.CDA.exception.UsernameAlreadyExistsException;
import com.CDA.model.User;
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

    // Constructor injection for all required beans
    @Autowired
    public UserService(JWTService jwtService, AuthenticationManager authManager,
                       UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' is already taken.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Verify user and generate JWT token
    public String verify(User user) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                User authenticatedUser = userRepository.findByUsername(user.getUsername());
                if (authenticatedUser == null) {
                    throw new UserNotFoundException("User with username '" + user.getUsername() + "' not found.");
                }

                User.Role role = authenticatedUser.getRole();
                if (role == null) {
                    throw new IllegalStateException("User role is not set.");
                }

                switch (role) {
                    case STUDENT:
                        // Handle student role, e.g., generate a token for a student
                        break;
                    case FACULTY:
                        // Handle faculty role, e.g., generate a token for a faculty member
                        break;
                    case ADMIN:
                        // Handle admin role, e.g., generate a token for an admin
                        break;
                    default:
                        throw new IllegalStateException("Unexpected role: " + role);
                }

                return jwtService.generateToken(user.getUsername()); // Generate token here
            }
        } catch (BadCredentialsException e) {
            return "Invalid username or password";
        }
        return "Authentication failed";
    }


    // Login functionality
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username '" + username + "' not found.");
        }
        // Validate password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password for username '" + username + "'.");
        }
        return user;
    }

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
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
