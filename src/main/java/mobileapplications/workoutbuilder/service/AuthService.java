package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.security.JwtUtil;
import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.exception.AuthServiceException;
import mobileapplications.workoutbuilder.exception.UserServiceException;
import mobileapplications.workoutbuilder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        if (email == "" || password == "") {
            throw new AuthServiceException("Email and password are required");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AuthServiceException("User with email " + email + " not found");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthServiceException("Invalid password");
        } else {
            return jwtUtil.generateToken(user);
        }
    }

    public User register(String name, String email, String password) {

        if (name == "" || email == "" || password == "") {
            throw new AuthServiceException("Name, email, and password are required");
        }

        if (!name.matches("^[a-zA-Z ]+$")) {
            throw new AuthServiceException("Name can only contain letters and spaces");
        }

        if (userRepository.findByEmail(email) != null) {
            throw new AuthServiceException("Email is already in use");
        }

        if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            throw new AuthServiceException("Invalid email format");
        }

        if (!isPasswordComplexEnough(password)) {
            throw new AuthServiceException("Password is not complex enough");
        }

        String encodedPassword = passwordEncoder.encode(password);

        return userService.createUser(name, email, encodedPassword);
    }

    public Boolean isPasswordComplexEnough(String password) {
        if (password.length() < 8) {
            throw new AuthServiceException("Password must be at least 8 characters long");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new AuthServiceException("Password must contain at least one number");
        } else if (!password.matches(".*[a-z].*")) {
            throw new AuthServiceException("Password must contain at least one lowercase letter");
        } else if (!password.matches(".*[A-Z].*")) {
            throw new AuthServiceException("Password must contain at least one uppercase letter");
        }

        return true;
    }

    public User updatePassword(Long id, String currentPassword, String newPassword) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new UserServiceException("User not found with ID: " + id);
        }

        if (!verifyPassword(id, currentPassword)) {
            throw new UserServiceException("Invalid password");
        }

        if (!isPasswordComplexEnough(newPassword)) {
            throw new UserServiceException("Password is not complex enough");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    public Boolean verifyPassword(Long userId, String password) {
        User user = userService.getUserById(userId);
        
        if (user == null) {
            throw new AuthServiceException("User not found with ID: " + userId);
        }
        
        return passwordEncoder.matches(password, user.getPassword());
    }
}
