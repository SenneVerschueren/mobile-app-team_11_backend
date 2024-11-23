package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.security.JwtUtil;
import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.exception.AuthServiceException;
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

        if (userRepository.findByEmail(email) != null) {
            throw new AuthServiceException("Email is already in use");
        }

        if (password.length() < 8) {
            throw new AuthServiceException("Password must be at least 8 characters long");
        }

        String encodedPassword = passwordEncoder.encode(password);

        return userService.createUser(name, email, encodedPassword);
    }
}
